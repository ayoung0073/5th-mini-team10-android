package com.moonayoung.greenlife.camera;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.moonayoung.greenlife.R;
import com.moonayoung.greenlife.intro.IntroActivity;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

public class CameraActivity extends AppCompatActivity implements AutoPermissionsListener {
    CameraView cameraView;
    int cameraFacing; // 후면인지 전면인지
    static CameraActivity fin; // 액티비티 종료시키기 위한 변수
    ImageView picture;
    Intent intent;
    Bitmap bitmap;
    Uri fileUri;
    CameraActivity me = this;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        fin = this;
        cameraFacing = Camera.CameraInfo.CAMERA_FACING_BACK;

        Button photoBT = findViewById(R.id.photoBT);
        Button galleryBT = findViewById(R.id.galleryBT);
        Button convertBT = findViewById(R.id.convertBT);

        AutoPermissions.Companion.loadAllPermissions(this,101); //실제로는 onDenied,onGranted 이런 거 오버라이딩 //이거 위치 중요

        final FrameLayout cameraFrame = findViewById(R.id.cameraFrame);
        cameraView = new CameraView(fin, cameraFacing);
        cameraFrame.addView(cameraView);



        galleryBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();

            }
        });

        convertBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 전면 -> 후면 or 후면 -> 전면으로 카메라 상태 전환

                cameraFacing = (cameraFacing==Camera.CameraInfo.CAMERA_FACING_FRONT) ?
                        Camera.CameraInfo.CAMERA_FACING_BACK
                        : Camera.CameraInfo.CAMERA_FACING_FRONT;

                cameraFrame.removeView(cameraView);

                // 변경된 방향으로 새로운 카메라 View 생성
                cameraView = new CameraView(fin, cameraFacing);
                cameraFrame.addView(cameraView);

            }
        });

        photoBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraView.capture(new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] bytes, Camera camera) {
                        bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                       send(bitmap);

                    }
                });
            }
        });


    }

    public void send(Bitmap bitmap){

        intent = new Intent(getApplicationContext(),ShareActivity.class);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 10, stream); // 이거 계속 바꾸니 됨 (1007) ㅁㅇㅇ

        byte[] bytes = stream.toByteArray();
        intent.putExtra("photo",bytes);
        intent.putExtra("file",fileUri);

        startActivityForResult(intent,102);
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

        public CameraView(Context context, int cameraFacing) {
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
                fileUri=data.getData();
                //이 intent의 getData() 메서드를 호출하면 Uri 자료형의 값이 반환됨
                //ContentResolver를 이용해 참조할 수 있는 이미지를 가리킴

                ContentResolver resolver=getContentResolver();
                try {
                    InputStream inputStream = resolver.openInputStream(fileUri);
                    //파라미터로 데이터 전달
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    bitmap= BitmapFactory.decodeStream(inputStream);
                    // 데이터로 받은 파일을 비트맵객체로 리턴
                    send(bitmap);

                    inputStream.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        else if(requestCode==102){
            me.finish();
        }
    }
}