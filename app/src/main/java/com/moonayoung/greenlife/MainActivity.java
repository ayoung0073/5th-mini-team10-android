package com.moonayoung.greenlife;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.moonayoung.greenlife.alarm.AlarmSetting;
import com.moonayoung.greenlife.feed.FragmentFeed;
import com.moonayoung.greenlife.challenge.FragmentChallenge;
import com.moonayoung.greenlife.rank.RankActivity;
import com.moonayoung.greenlife.setting.FragmentSetting;
import com.moonayoung.greenlife.intro.IntroActivity;
import com.moonayoung.greenlife.intro.LoadingActivity;

public class MainActivity extends AppCompatActivity {

    private static int ONE_MINUTE = 5626;

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentChallenge fragmentChallenge = new FragmentChallenge();
    private FragmentFeed fragmentFeed = new FragmentFeed();
    private FragmentSetting fragmentSetting = new FragmentSetting();

    private String token;

    FragmentTransaction transaction;

    MainActivity me = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new AlarmSetting(getApplicationContext()).Alarm();

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentChallenge).commitAllowingStateLoss();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                switch (menuItem.getItemId()) {
                    case R.id.challengeItem:
                        transaction.replace(R.id.frameLayout, fragmentChallenge).commitAllowingStateLoss();
                        break;
                    case R.id.feedItem:
                        transaction.replace(R.id.frameLayout, fragmentFeed).commitAllowingStateLoss();
/*                        Intent intent = new Intent(me, RankActivity.class);
                        startActivity(intent); // 임시로 명전 띄우기*/
                        break;
                    case R.id.settingItem:
                        transaction.replace(R.id.frameLayout, fragmentSetting).commitAllowingStateLoss();
                        break;
                }

                /* 똑똑~ 오늘의 작은 실천을 보여주세요! */
                return true;
            }
        });




        Intent intent = new Intent(this, LoadingActivity.class);
        startActivityForResult(intent, 101);
    }

    public void back_setting(){
        transaction.replace(R.id.frameLayout, fragmentSetting).commitAllowingStateLoss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101) { // 로딩액티비티 끝나고 올 때, IntroActivity 시작시키기
            Intent intent2 = new Intent(this, IntroActivity.class);
            startActivity(intent2);
        }
    }

    public void setToken(String token){
        this.token = token;
    }

    public String getToken(){
        return token;
    }
}