package com.moonayoung.greenlife;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewDebug;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, LoadingActivity.class);
        startActivityForResult(intent, 101);

    }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
                super.onActivityResult(requestCode, resultCode, data);

                if(requestCode == 101){ // 로딩액티비티 끝나고 올 때, IntroActivity 시작시키기
                        Intent intent2 = new Intent(this, IntroActivity.class);
                        startActivity(intent2);
                }
        }
}