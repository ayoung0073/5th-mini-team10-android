package com.moonayoung.greenlife;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CameraActivity extends AppCompatActivity implements AutoPermissionsListener {
    CameraView cameraView;
    int cameraFacing; // 후면인지 전면인지
    static CameraActivity fin; // 액티비티 종료시키기 위한 변수

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        fin = this;
        cameraFacing = Camera.CameraInfo.CAMERA_FACING_BACK;

        Button photoBT = findViewById(R.id.photoBT);
        Button galleryBT = findViewById(R.id.galleryBT);
        Button convertBT = findViewById(R.id.convertBT);

        AutoPermissions.Companion.loadAllPermissions(this,101); //실제로는 onDenied,onGranted 이런 거 오버라이딩 //이거 위치 중요

        FrameLayout cameraFrame = findViewById(R.id.cameraFrame);
        cameraView = new CameraView(this);
        cameraFrame.addView(cameraView);



        galleryBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"갤러리보기버튼",Toast.LENGTH_LONG).show();
                openGallery();

            }
        });

        convertBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"카메라전환버튼",Toast.LENGTH_LONG).show();
            }
        });

        photoBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"촬영버튼",Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onDenied(int i, String[] strings) {

    }

    @Override
    public void onGranted(int i, String[] strings) {

    }

    public class CameraView extends SurfaceView implements SurfaceHolder.Callback {
        // SurfaceView 클래스를 상속, Callback 인터페이스를 구현하는 클래스 정의
        SurfaceHolder holder;
        Camera camera = null;

        public CameraView(Context context) {
            super(context);

            init(context);
        }

        public CameraView(Context context, AttributeSet attrs) { //초기화 작업 한곳에 하기 위해 init메서드 호출
            super(context, attrs);

            init(context);
        }

        public void init(Context context) {
            holder = getHolder();
            holder.addCallback(this);   //구현한 콜백인터페이스 객체가 카메라서비스뷰
        }

        @Override
        public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
            camera= camera.open(); //Surface뷰 만들어질 때 카메라 객체 참조
            try {
                camera.setPreviewDisplay(holder);// 미리보기 화면으로 홀더 객체 생성
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
            camera.startPreview();//카메라 미리보기
        }


        @Override
        public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
            camera.stopPreview(); // 서피스뷰 없어지면 미리보기 stop
            camera.release();
            camera = null;
        }


        public boolean capture(Camera.PictureCallback callback) { // 사진 촬영
            if (camera != null) {  //takePicture 메서드 호출 //하드웨어 쪽에 있는 카메라 메서드
                camera.takePicture(null, null, callback);
                return true;
            } else {
                return false;
            }
        }
    }

    public void openGallery(){ //갤러리 가져오기
        Intent intent = new Intent();
        intent.setType("image/*");
        // MIME 타입이 image로 시작하는 데이터 가져오기
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(intent,101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // 다른 화면으로부터 응답올 때 호출되는 메서드 오버라이딩
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==101)
        {
            if(resultCode==RESULT_OK) //사진 선택일 때 정상 ok온다
            {
                Uri fileUri=data.getData();
                //이 intent의 getData() 메서드를 호출하면 Uri 자료형의 값이 반환됨
                //ContentResolver를 이용해 참조할 수 있는 이미지를 가리킴

                ContentResolver resolver=getContentResolver();
                try {
                    InputStream inputStream = resolver.openInputStream(fileUri);
                    //파라미터로 데이터 전달
                    Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                    // 데이터로 받은 파일을 비트맵객체로 리턴

                    inputStream.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}