package com.moonayoung.greenlife.challenge;

import android.app.AlertDialog;
import android.app.Dialog;
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
import com.moonayoung.greenlife.challenge.ChallengeData;

public class ChallengeFragment1 extends Fragment {

    ChallengeData data = new ChallengeData();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_challenge1, container, false);
        TextView content1 = rootView.findViewById(R.id.content1);
        content1.setText(data.challengeLists.get(0).getContent());

        return rootView;
    }
}
