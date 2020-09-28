package com.moonayoung.greenlife.setting;

import android.content.Context;
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

import com.moonayoung.greenlife.R;
import com.moonayoung.greenlife.challenge.MainActivity;

import org.w3c.dom.Text;

public class FragmentSetting extends Fragment {
    private Button buttonAccount;
    private Button buttonNotice;
    /*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity= (MainActivity) getActivity();
    }*/

    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        view.findViewById(R.id.btn_accountSetting).setOnClickListener((View.OnClickListener) this); //계정설정
        view.findViewById(R.id.btn_notice).setOnClickListener((View.OnClickListener) this); //공지사항
        view.findViewById(R.id.txt_question).setOnClickListener((View.OnClickListener) this); //문의하기
        view.findViewById(R.id.txt_version).setOnClickListener((View.OnClickListener) this); //버전정보

        buttonAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),AccountSettings.class);
                startActivity(intent);
            }
        });

        /*buttonNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }
}

