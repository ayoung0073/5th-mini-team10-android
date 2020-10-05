package com.moonayoung.greenlife.intro;

import android.os.Bundle;
<<<<<<< HEAD
import android.widget.FrameLayout;
import android.widget.Toast;
=======
>>>>>>> cdacd8a00b01d369310581d86ee8246f85d7f937

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.moonayoung.greenlife.R;

import java.util.ArrayList;

public class IntroActivity extends AppCompatActivity {
<<<<<<< HEAD
    IntroFragment1 introFragment1;
    IntroFragment2 introFragment2;
    IntroFragment3 introFragment3;

    ViewPager container;
    FrameLayout frameContainer;
=======
    CardNewsFragment cardNewsFragment;
    IntroFragment introFragment;
    SuggestFragment suggestFragment;

    ViewPager container;
>>>>>>> cdacd8a00b01d369310581d86ee8246f85d7f937

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        FragmentManager manager = getSupportFragmentManager();

<<<<<<< HEAD
        introFragment1 = new IntroFragment1(); // 처음 보여지는 Fragment
        introFragment2 = new IntroFragment2(); // 어플 소개 Fragment
        introFragment3 = new IntroFragment3(); // 참여 유도 Fragment
=======
        cardNewsFragment = new CardNewsFragment(); // 처음 보여지는 Fragment
        introFragment = new IntroFragment(); // 어플 소개 Fragment
        suggestFragment = new SuggestFragment(); // 참여 유도 Fragment
>>>>>>> cdacd8a00b01d369310581d86ee8246f85d7f937

        container = findViewById(R.id.container);
        container.setOffscreenPageLimit(3); // 뷰페이저 3장 제한

<<<<<<< HEAD
        frameContainer = findViewById(R.id.frameContainer);

=======
>>>>>>> cdacd8a00b01d369310581d86ee8246f85d7f937

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
<<<<<<< HEAD
        adapter.addItem(introFragment1); // 뷰페이저에 프래그먼트 담기
        adapter.addItem(introFragment2);
        adapter.addItem(introFragment3);
=======
        adapter.addItem(cardNewsFragment); // 뷰페이저에 프래그먼트 담기
        adapter.addItem(introFragment);
        adapter.addItem(suggestFragment);
>>>>>>> cdacd8a00b01d369310581d86ee8246f85d7f937


        container.setAdapter(adapter);
    }

<<<<<<< HEAD
    public void setFragment(String str){
        //getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        getSupportFragmentManager().beginTransaction().remove(introFragment1).commit(); // 프래그먼트 없애기
        getSupportFragmentManager().beginTransaction().remove(introFragment2).commit();
        getSupportFragmentManager().beginTransaction().remove(introFragment3).commit();

        if(str.equals("join")){
            Toast.makeText(getApplicationContext(),str+"눌림",Toast.LENGTH_LONG).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, new JoinFragment()).commit();
            //onAttachFragment(new JoinFragment());
        }
        else if(str.equals("login")){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, new LoginFragment()).commit();
            //onAttachFragment(new LoginFragment());
        }
    }

=======
>>>>>>> cdacd8a00b01d369310581d86ee8246f85d7f937
    public void setFinish() { // 메서드 호출되면 액티비티 끝냄
        finish();
    }
}