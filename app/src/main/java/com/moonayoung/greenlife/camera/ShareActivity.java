package com.moonayoung.greenlife.camera;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonArray;
import com.moonayoung.greenlife.R;
import com.moonayoung.greenlife.api.Feed;
import com.moonayoung.greenlife.api.JoinPost;
import com.moonayoung.greenlife.api.Participate;
import com.moonayoung.greenlife.api.RetrofitClient;
import com.moonayoung.greenlife.api.SubChallenge;
import com.moonayoung.greenlife.api.UploadPost;
import com.moonayoung.greenlife.intro.IntroActivity;
import com.moonayoung.greenlife.intro.LoginFragment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ShareActivity extends AppCompatActivity {
    private static final int PICK_FROM_CAMERA= 1;
    private static final int PICK_FROM_ALBUM = 2;

    private Uri mImageCaptureUri;
    private Bitmap mImageBitmap;
    String imagePath;
    Context mContext;

    ImageView imageView;

    Button shareBT;
    Intent intent;
    Bitmap bitmap;
    File imageFile = null;
    String fileName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        intent = getIntent();
        byte[] bytes = intent.getByteArrayExtra("photo");
        bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);


        imageView = findViewById(R.id.photo);
        //선택된 사진 받아서 서버에 업로드
        imageView.setImageBitmap(bitmap);

        /*imageFile = bitmapToFile(bitmap,"image1");
        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), imageFile);
        final MultipartBody.Part body = MultipartBody.Part.createFormData("image",fileName ,reqFile);

        shareBT = findViewById(R.id.shareBT);
        shareBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload(body);
            }
        });

    }

    public void upload(MultipartBody.Part body){
        RetrofitClient.getApiService().postPhoto(LoginFragment.getToken(),body).enqueue(new Callback<UploadPost>() {
            @Override
            public void onResponse(Call<UploadPost> call, Response<UploadPost> response) {
                System.out.println(response.toString());
                if(response.isSuccessful()){
                    Log.d("피드",""+response.body().isSuccess());
                }
                else{
                    Log.d("응답이상", "" + response.code());
                    MainActivity mainActivity = (MainActivity)getActivity();
                    String token = mainActivity.getToken();

                }
            }
            @Override
            public void onFailure(Call<UploadPost> call, Throwable t) {
                System.out.println(t.getMessage());
                Log.d("fjkad","=========================================failedfailed");
            }
        });*/
    }

    /*public File bitmapToFile(Bitmap bitmap, String fileNameToSave){ // File name like "image.png"
        //create a file to write bitmap data
        File file = null;
        fileName = fileNameToSave;
        try {
            file = new File(Environment.getExternalStorageDirectory().toString() + File.separator + fileNameToSave);
            file.createNewFile();

            //Convert bitmap to byte array
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos); // YOU can also save it in JPEG
            byte[] bitmapdata = bos.toByteArray();

            //write the bytes in file
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return file; // it will return null
        }
    }*/
//    private File createFileFromBitmap(Bitmap bitmap) throws IOException {
//        File newFile = new File(this.getFilesDir(), makeImageFileName());
//        FileOutputStream fileOutputStream = new FileOutputStream(newFile);
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
//        fileOutputStream.close();
//        return newFile;
//    }
}



