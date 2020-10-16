package com.moonayoung.greenlife.camera;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.moonayoung.greenlife.MainActivity;
import com.moonayoung.greenlife.R;
import com.moonayoung.greenlife.api.Feed;
import com.moonayoung.greenlife.api.RetrofitClient;
import com.moonayoung.greenlife.api.UploadPost;
import com.moonayoung.greenlife.feed.FragmentFeed;
import com.moonayoung.greenlife.intro.IntroActivity;
import com.moonayoung.greenlife.intro.LoginFragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ShareActivity extends AppCompatActivity {
    private static final int REQUEST_FEED= 1;

    ImageView imageView;

    Button shareBT;
    Intent intent;
    static Bitmap bitmap;

    Button backBT;
    Uri fileUri;

    ShareActivity me = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        intent = getIntent();
        byte[] bytes = intent.getByteArrayExtra("photo");
        fileUri = Uri.parse(String.valueOf(intent.getParcelableExtra("fileUri")));
        //System.out.println(fileUri.getPath());
        bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        backBT = findViewById(R.id.backBT3);
        backBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                me.finish();
            }
        });


        imageView = findViewById(R.id.photo);
        //선택된 사진 받아서 서버에 업로드
        imageView.setImageBitmap(bitmap);

        shareBT = findViewById(R.id.shareBT);
        shareBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload(bitmap);

                AlertDialog.Builder ad = new AlertDialog.Builder(me,R.style.MyAlertDialogStyle);
                ad.setTitle(LoginFragment.getNickname()+"님"); //username
                ad.setMessage("참여 감사합니다 :) \n 당신의 실천이 \n 일상이 되길 바랍니다.\n오늘의 작은 실천들이 모여 \n 지구를 지켜가는 중입니다. \n 다른 챌린지도 함께 해 주세요.");
                ad.setIcon(R.drawable.alert);

                ad.show();



            }
        });
    }

    public void upload(Bitmap bitmap){
        String filename="iamge.jpg"; // %2E

        File storageDir = Environment.getExternalStorageDirectory();
        File file = new File(storageDir, filename);


        //String ex_storage =Environment.getExternalStorageDirectory().getAbsolutePath();

        try{
            file = new File(storageDir, filename);

            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.close();
        }catch(FileNotFoundException exception){
            Log.e("FileNotFoundException", exception.getMessage());
        }
        catch(IOException exception){ Log.e("IOException", exception.getMessage()); }



        //RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        RequestBody requestFile = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", filename, RequestBody.create(MediaType.parse("multipart/form-data"), file))
                .build();
        //formData.append('image', 파일);
        //MultipartBody.Part uploadFile = MultipartBody.Part.createFormData("image", file.getName(), requestFile);


/*        HashMap<String, MultipartBody.Part> map = new HashMap<>();
        map.put("img",uploadFile);*/

        RetrofitClient.getApiService().postPhoto(LoginFragment.getToken(),requestFile).enqueue(new Callback<UploadPost>() {
            @SuppressLint("ResourceType")
            @Override
            public void onResponse(Call<UploadPost> call, Response<UploadPost> response) {
                Log.d("upload","통신성공");
                UploadPost upload = response.body();
                Log.d("upload",""+upload.isSuccess());
                Log.d("upload",""+response.toString());

                if(upload.isSuccess()==true){
                    Toast.makeText(getApplicationContext(),"업로드 완료하였습니다", Toast.LENGTH_SHORT).show();

                    imageView.setImageBitmap(null);
                    imageView = null;

                    //FragmentFeed fragmentFeed = new FragmentFeed();

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.container, new FragmentFeed());
                    transaction.commit();


                }
                else {
                    Toast.makeText(getApplicationContext(),"업로드 실패하였습니다", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<UploadPost> call, Throwable t) {

            }
        });
    }


}



