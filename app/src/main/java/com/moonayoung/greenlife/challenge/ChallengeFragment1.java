package com.moonayoung.greenlife.challenge;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.moonayoung.greenlife.CameraActivity;
import com.moonayoung.greenlife.R;

public class ChallengeFragment1 extends Fragment {

    ChallengeData data = new ChallengeData();

    Button btn1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_challenge1,container,false);
        TextView content1 = rootView.findViewById(R.id.content1);
        content1.setText(data.challengeLists.get(0).getContent());
        return rootView;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setOnContentView(R.layout.fragment_challenge1);

        //btn1 = (Button) btn1.findViewById(0);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
                ad.setIcon(null);
                ad.setTitle(null); //user name
                ad.setMessage("참여 감사합니다 :) \n 당신의 실천이 \n 일상이 되길 바랍니다.");

                ad.setPositiveButton("사진으로 인증하기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick (DialogInterface dialog, int which) {
                        Intent intent = new Intent(getActivity(), CameraActivity.class); //go to camera
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });

                ad.setNegativeButton("인증은 안 할래요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                ad.show();
            }
        });
    }

}

