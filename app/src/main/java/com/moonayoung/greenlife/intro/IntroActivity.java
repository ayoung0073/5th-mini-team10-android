package com.moonayoung.greenlife.intro;

import android.graphics.Paint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.moonayoung.greenlife.R;
import com.moonayoung.greenlife.intro.IntroFragment2;
import com.moonayoung.greenlife.intro.IntroFragment3;
import com.moonayoung.greenlife.intro.JoinFragment;
import com.moonayoung.greenlife.intro.LoginFragment;

import java.util.ArrayList;

import retrofit2.http.HEAD;

public class IntroActivity extends AppCompatActivity {
    IntroFragment1 introFragment1;
    IntroFragment2 introFragment2;
    IntroFragment3 introFragment3;

    ViewPager container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        FragmentManager manager = getSupportFragmentManager();

        introFragment1 = new IntroFragment1(); // 처음 보여지는 Fragment
        introFragment2 = new IntroFragment2(); // 어플 소개 Fragment
        introFragment3 = new IntroFragment3(); // 참여 유도 Fragment


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
        adapter.addItem(introFragment1); // 뷰페이저에 프래그먼트 담기
        adapter.addItem(introFragment2);
        adapter.addItem(introFragment3);


        container.setAdapter(adapter);
    }

    public void setFragment(String str){
        //getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        getSupportFragmentManager().beginTransaction().remove(introFragment1).commit(); // 프래그먼트 없애기
        getSupportFragmentManager().beginTransaction().remove(introFragment2).commit();
        getSupportFragmentManager().beginTransaction().remove(introFragment3).commit();

        if(str.equals("join")){
            Toast.makeText(getApplicationContext(),str+"눌림",Toast.LENGTH_LONG).show();
            getSupportFragmentManager().beginTransaction().add(R.id.frameContainer, new JoinFragment()).commit();

            //1005 replace하면 오류 ! 앞에서 프래그먼트 다 remove해서 그런 듯 add해야함

            //onAttachFragment(new JoinFragment());
        }
        else if(str.equals("login")){
            getSupportFragmentManager().beginTransaction().add(R.id.frameContainer, new LoginFragment()).commit();
            //onAttachFragment(new LoginFragment());
        }
    }


    public void setFinish() { // 메서드 호출되면 액티비티 끝냄
        finish();
    }
}