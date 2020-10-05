<<<<<<< HEAD

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

public class ChallengeFragment1 extends Fragment {

    ChallengeData data = new ChallengeData();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_challenge1,container,false);
        TextView content1 = rootView.findViewById(R.id.content1);
        content1.setText(data.challengeLists.get(0).getContent());
        return rootView;
    }
}
=======
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

public class ChallengeFragment1 extends Fragment {

    ChallengeData data = new ChallengeData();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_challenge1,container,false);
        TextView content1 = rootView.findViewById(R.id.content1);
        content1.setText(data.challengeLists.get(0).getContent());
        return rootView;
    }
}
>>>>>>> 84185c7cb89ef8c2b0f28b21d73f62ed3504f8fa
