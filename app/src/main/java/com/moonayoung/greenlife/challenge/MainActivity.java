package com.moonayoung.greenlife.challenge;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

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
import com.moonayoung.greenlife.intro.IntroActivity;
import com.moonayoung.greenlife.intro.LoadingActivity;
import com.moonayoung.greenlife.R;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentChallenge fragmentChallenge = new FragmentChallenge();
    private FragmentFeed fragmentFeed = new FragmentFeed();
    private FragmentSetting fragmentSetting = new FragmentSetting();
    RecyclerView challengeListView;
    ChallengeAdapter adapter;

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
        challengeListView = findViewById(R.id.challengListView); // 챌린지 목록 보여줄 리싸이클러뷰

        fragmentManager = getSupportFragmentManager();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false); //linearlayout으로 리싸이클러뷰 설정
        challengeListView.setLayoutManager(layoutManager);

        Intent intent = new Intent(this, LoadingActivity.class);
        startActivityForResult(intent, 101);

        ChallengeList challengeList = new ChallengeList();
        adapter = new ChallengeAdapter();
//        adapter.setItems(challengeList.getChallengeLists()); // 데이터 저장되어 있음 Title, content, 세부 챌린지 배열
        challengeListView.setAdapter(adapter); // 어댑터에 설정 -> 리싸이클러뷰에 챌린지 목록 보임

        adapter.setOnItemClickListener(new onChallengeListClickListener() {
            @Override
            public void onItemClick(ChallengeAdapter.ViewHolder holder, View view, int position) { // 챌린지 목록 각각 눌렀을 때
                ChallengeList item = adapter.getItem(position);
                position++;
                //Toast.makeText(getApplicationContext(), position + "번째 목록 선택됨",Toast.LENGTH_LONG).show();

                switch (position) {
                    case 1: // 첫번째 click
                        fragmentManager.beginTransaction().replace(R.id.mainContainer, new ChallengeFragment1()).commit(); //1번째 챌린지 프래그먼트 띄움
                        break;
                    case 2:
                        fragmentManager.beginTransaction().replace(R.id.mainContainer, new ChallengeFragment2()).commit();
                        break;
                    case 3:
                        fragmentManager.beginTransaction().replace(R.id.mainContainer, new ChallengeFragment3()).commit();
                        break;
                    case 4:
                        fragmentManager.beginTransaction().replace(R.id.mainContainer, new ChallengeFragment4()).commit();
                        break;
                    case 5:
                        fragmentManager.beginTransaction().replace(R.id.mainContainer, new ChallengeFragment5()).commit();
                        break;
                }

            }
        });

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