package com.moonayoung.greenlife.challenge;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
<<<<<<< HEAD
=======
import android.view.View;
>>>>>>> 84185c7cb89ef8c2b0f28b21d73f62ed3504f8fa

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.moonayoung.greenlife.FragmentFeed;
import com.moonayoung.greenlife.FragmentSetting;
<<<<<<< HEAD
import com.moonayoung.greenlife.api.ApiService;
import com.moonayoung.greenlife.challenge.FragmentChallenge;
=======
>>>>>>> 84185c7cb89ef8c2b0f28b21d73f62ed3504f8fa
import com.moonayoung.greenlife.intro.IntroActivity;
import com.moonayoung.greenlife.intro.LoadingActivity;
import com.moonayoung.greenlife.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.HEAD;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentChallenge fragmentChallenge = new FragmentChallenge();
    private FragmentFeed fragmentFeed = new FragmentFeed();
    private FragmentSetting fragmentSetting = new FragmentSetting();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
=======
>>>>>>> 84185c7cb89ef8c2b0f28b21d73f62ed3504f8fa

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
}