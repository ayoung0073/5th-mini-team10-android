package com.moonayoung.greenlife.challenge;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.moonayoung.greenlife.FragmentFeed;
import com.moonayoung.greenlife.FragmentSetting;
import com.moonayoung.greenlife.intro.IntroActivity;
import com.moonayoung.greenlife.intro.LoadingActivity;
import com.moonayoung.greenlife.R;

import java.util.Stack;

import static com.moonayoung.greenlife.challenge.FragmentChallenge.fragmentStack;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentChallenge fragmentChallenge = new FragmentChallenge();
    private FragmentFeed fragmentFeed = new FragmentFeed();
    private FragmentSetting fragmentSetting = new FragmentSetting();
    public static Stack<Fragment> fragmentStack; //프래그먼트 뒤로가기를 위한 프래그먼트 스택

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
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
                        break;
                    case R.id.settingItem:
                        transaction.replace(R.id.frameLayout, fragmentSetting).commitAllowingStateLoss();
                        break;
                }
                return true;
            }
        });





        Intent intent = new Intent(this, LoadingActivity.class);
        startActivityForResult(intent, 101);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101) { // 로딩액티비티 끝나고 올 때, IntroActivity 시작시키기
            Intent intent2 = new Intent(this, IntroActivity.class);
            startActivity(intent2);
        }
    }

    //뒤로가기 버튼 눌렀을 때 호출되는 함수 오버라이드
    @Override
    public void onBackPressed() {
        if(!fragmentStack.isEmpty()){
            Fragment nextFragment = fragmentStack.pop();
            fragmentManager.beginTransaction().replace(R.id.container, nextFragment).commit();
            System.out.println("[TESTING >>] " + fragmentStack.size());
        }else {
            super.onBackPressed();
        }

    }
}