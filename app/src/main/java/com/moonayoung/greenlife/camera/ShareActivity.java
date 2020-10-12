package com.moonayoung.greenlife.camera;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.moonayoung.greenlife.R;
import com.moonayoung.greenlife.api.RetrofitClient;


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

        shareBT = findViewById(R.id.shareBT);
        shareBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload(bitmap);
            }
        });


      
    }
    public void upload(Bitmap bitmap){
        //RetrofitClient.getApiService().postPhoto(,)
    }
}



