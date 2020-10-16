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
import androidx.core.content.FileProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.moonayoung.greenlife.MainActivity;
import com.moonayoung.greenlife.R;
import com.moonayoung.greenlife.api.Feed;
import com.moonayoung.greenlife.api.RetrofitClient;
import com.moonayoung.greenlife.api.UploadPost;
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
    private static final int PICK_FROM_CAMERA= 1;
    private static final int PICK_FROM_ALBUM = 2;

    private Uri mImageCaptureUri;
    private Bitmap mImageBitmap;
    String imagePath;
    Context mContext;

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
            }
        });



    }
    public void upload(Bitmap bitmap){
        File file;
        String filename="img.jpg";

        File storageDir = Environment.getExternalStorageDirectory();
        file = new File(storageDir, filename);


        String ex_storage =Environment.getExternalStorageDirectory().getAbsolutePath();

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
        MultipartBody.Part uploadFile = MultipartBody.Part.createFormData("image", fileUri.getEncodedPath(), requestFile);


/*        HashMap<String, MultipartBody.Part> map = new HashMap<>();
        map.put("img",uploadFile);*/

        RetrofitClient.getApiService().postPhoto(LoginFragment.getToken(),uploadFile).enqueue(new Callback<UploadPost>() {
            @Override
            public void onResponse(Call<UploadPost> call, Response<UploadPost> response) {
                Log.d("upload","통신성공");
                UploadPost upload = response.body();
                Log.d("upload",""+upload.isSuccess());
                Log.d("upload",""+response.toString());

                if(upload.isSuccess()==true){
                    Toast.makeText(getApplicationContext(),"업로드 완료하였습니다", Toast.LENGTH_SHORT).show();
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



