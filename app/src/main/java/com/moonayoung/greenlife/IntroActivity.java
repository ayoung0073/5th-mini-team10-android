package com.moonayoung.greenlife;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class IntroActivity extends AppCompatActivity {
    CardNewsFragment cardNewsFragment;
    IntroFragment introFragment;
    SuggestFragment suggestFragment;

    ViewPager container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        FragmentManager manager = getSupportFragmentManager();

        cardNewsFragment = new CardNewsFragment(); // 처음 보여지는 Fragment
        introFragment = new IntroFragment(); // 어플 소개 Fragment
        suggestFragment = new SuggestFragment(); // 참여 유도 Fragment

        container = findViewById(R.id.container);
        container.setOffscreenPageLimit(3); // 뷰페이저 3장 제한


        class PagerAdapter extends FragmentStatePagerAdapter { // 뷰페이저 어댑터
            ArrayList<Fragment> items = new ArrayList<Fragment>();

            public PagerAdapter(FragmentManager fm) {
                super(fm);
            }


            @NonNull
            @Override
            public Fragment getItem(int position) {
                return items.get(position);
            }

            @Override
            public int getCount() {
                return items.size();
            }

            public void addItem(Fragment item) {
                items.add(item);
            }
        }

        PagerAdapter adapter = new PagerAdapter(manager);
        adapter.addItem(cardNewsFragment); // 뷰페이저에 프래그먼트 담기
        adapter.addItem(introFragment);
        adapter.addItem(suggestFragment);


        container.setAdapter(adapter);
    }

    public void setFinish() { // 메서드 호출되면 액티비티 끝냄
        finish();
    }

}