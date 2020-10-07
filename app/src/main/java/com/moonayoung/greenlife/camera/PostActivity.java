package com.moonayoung.greenlife.camera;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.moonayoung.greenlife.R;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Intent intent = getIntent();
        final Uri photoUri = intent.getData();

        ImageView ivResult = (ImageView) findViewById(R.id.iv_result);

        findViewById(R.id.btnShare).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                post(photoUri.toString());
                AlertDialog.Builder ad = new AlertDialog.Builder(PostActivity.this);
                ad.setIcon(R.mipmap.ic_launcher);
                ad.setIcon(null);
                ad.setTitle(null); //여기 user id 들어가야 함
                ad.setMessage("참여 감사합니다 :) \n 오늘의 작은 실천들이 모여 \n " +
                        "지구를 지켜가는 중입니다. \n 다른 챌린지도 함께 해 주세요.");
            }
        });

        Glide.with(this)
                .load(photoUri)
                .centerCrop()
                .into(ivResult);
    }

    private void post(String uriString) {
        PostTask postTask = new PostTask();
        postTask.execute(uriString);
    }


    class PostTask extends AsyncTask<String, Void, Boolean> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(PostActivity.this, "포스트 업로드 중", "잠시만 기다려주세요",true, false);

        }

        @Override
        protected Boolean doInBackground(String... strings) {
            //파일 준비
            Uri imageUri = Uri.parse(strings[0]);

            try {
                Bitmap bitmap = getImageBitmapFromUri(imageUri);
                File imageFile = createFileFromBitmap(bitmap);

                /*
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("uploader", "g82")
                        .addFormDataPart("image", makeImageFileName(),
                                RequestBody.create(MediaType.parse("image/png")))
                        .build();

                Request request = new Request.Builder()
                        .url(Api.UP_POST)
                        .post(requestBody)
                        .build();

                OkHttpClient okHttpClient = new OkHttpClient();

                Response response = okHttpClient.newCall(request).execute(); */

            } catch (IOException e) {
                Log.d("PostTask", "post failed", e);
                return false;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            progressDialog.dismiss();
            if(aBoolean) {
                Toast.makeText(PostActivity.this, "success", Toast.LENGTH_SHORT).show();
                Log.d("PostTask", "success");
            }
            else Log.d("PostTask", "failed");
            }
        }


    //Uri -> Bitmap -> File

    public Bitmap getImageBitmapFromUri(Uri uri) throws IOException {

        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

    public File createFileFromBitmap (Bitmap bitmap) throws IOException {

        File newFile = new File(getFilesDir(), makeImageFileName());

        FileOutputStream fileOutputStream = new FileOutputStream(newFile);

        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);

        fileOutputStream.close();

        return newFile;
    }

    public String makeImageFileName() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_hhmmss");
        Date date = new Date();
        String strDate = simpleDateFormat.format(date);
        return strDate+".png";
    }
}