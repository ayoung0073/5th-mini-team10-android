package com.moonayoung.greenlife.camera;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.moonayoung.greenlife.CameraActivity;
import com.moonayoung.greenlife.R;
import com.moonayoung.greenlife.challenge.ChallengeAdapter;
import com.moonayoung.greenlife.challenge.ChallengeData;

public class ShareActivity extends AppCompatActivity {
    private static final int PICK_FROM_CAMERA= 1;
    private static final int PICK_FROM_ALBUM = 2;

    private Uri mImageCaptureUri;
    private Bitmap mImageBitmap;
    String imagePath;
    Context mContext;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        imageView = (ImageView) findViewById(R.id.photo);
        //선택된 사진 받아서 서버에 업로드
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(imagePath)) {
                    if(NetworkHelper.checkConnection(mContext)) { //인터넷 연결 체크
                        String ImageUploadURL = "";
                        new ImageUploadTask().execute(ImageUploadURL, imagePath);
                } else {
                        Toast.makeText(mContext, "인터넷 연결을 확인하세요", Toast.LENGTH_LONG).show();
                    }
            }else {
                    Toast.makeText(mContext, "먼저 업로드할 파일을 선택하세요", Toast.LENGTH_SHORT).show();
                }
        });


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_share, container, false);

        Button share = rootView.findViewById(R.id.share);

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //팝업창
                AlertDialog.Builder ad = new AlertDialog.Builder(ShareActivity.this);
                ad.setTitle("님"); //username
                ad.setMessage("참여 감사합니다 :) \n 오늘의 작은 실천들이 모여 \n 지구를 지켜가는 중입니다.\n 다른 챌린지도 함께 해 주세요.");
                ad.show();
            }
        });
        return rootView;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_OK) return;
        switch (requestCode){
            case PICK_FROM_ALBUM: {
                if(data==null) {
                    return;
                }
                Uri selectedImageUri = data.getData();

            }
        }
    }

}