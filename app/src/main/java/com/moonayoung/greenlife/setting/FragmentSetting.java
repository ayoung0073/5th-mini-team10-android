package com.moonayoung.greenlife.setting;

import android.app.Activity;
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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.moonayoung.greenlife.R;
import com.moonayoung.greenlife.challenge.MainActivity;

import org.w3c.dom.Text;

public class FragmentSetting extends Fragment {
    private Button buttonAccount;
    private Button buttonNotice;
    private Activity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity= (MainActivity) getActivity();
    }

    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        buttonAccount = (Button)view.findViewById(R.id.btn_accountSetting);

        buttonAccount.setOnClickListener(new View.OnClickListener() { //계정설정 버튼 눌렀을 때 accountSetting activity로 전환
            @Override
            public void onClick(View view) {
                //getActivity().startActivity(new Intent(requireActivity(), AccountSettings.class));
                //ft=manager.beginTransaction();
                //ft.replace(R.id.)
                /*((AccountSettings)getActivity()).findViewById(R.id.btn_accountSetting);
                activity.onFragmentChange(0);*/

                Intent intent = new Intent(getActivity(),AccountSettings.class);
                startActivity(intent);
            }
        });


/*        buttonNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
        return view;
    }
}

