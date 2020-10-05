package com.moonayoung.greenlife.challenge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.moonayoung.greenlife.R;

public class ChallengeFragment3 extends Fragment {

    ChallengeData data = new ChallengeData();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_challenge3,container,false);
        TextView content = rootView.findViewById(R.id.content3);
        content.setText(data.challengeLists.get(2).getContent());
        return rootView;
    }
}

