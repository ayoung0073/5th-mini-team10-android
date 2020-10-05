package com.moonayoung.greenlife.intro;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.moonayoung.greenlife.CameraActivity;
import com.moonayoung.greenlife.R;

public class IntroFragment3 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_intro3, container, false);

        Button loginBT = rootView.findViewById(R.id.loginBT);
        loginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent2 = new Intent(getContext(), CameraActivity.class); // 카메라 연습하려고 껴둠
                startActivity(intent2);*/
                ((IntroActivity)getActivity()).setFragment("login");
            }
        });

        Button joinBT = rootView.findViewById(R.id.joinBT);
        joinBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent2 = new Intent(getContext(), CameraActivity.class); // 카메라 연습하려고 껴둠
                startActivity(intent2);*/
                ((IntroActivity)getActivity()).setFragment("join");
            }
        });

        return rootView;
    }
}
