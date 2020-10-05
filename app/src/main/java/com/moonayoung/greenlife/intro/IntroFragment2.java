package com.moonayoung.greenlife.intro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.moonayoung.greenlife.R;

<<<<<<< HEAD:app/src/main/java/com/moonayoung/greenlife/intro/IntroFragment2.java
public class IntroFragment2 extends Fragment {
=======
public class IntroFragment extends Fragment {
>>>>>>> cdacd8a00b01d369310581d86ee8246f85d7f937:app/src/main/java/com/moonayoung/greenlife/intro/IntroFragment.java

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
<<<<<<< HEAD:app/src/main/java/com/moonayoung/greenlife/intro/IntroFragment2.java
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_intro2, container, false);
=======
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_intro, container, false);
>>>>>>> cdacd8a00b01d369310581d86ee8246f85d7f937:app/src/main/java/com/moonayoung/greenlife/intro/IntroFragment.java
        return rootView;
    }
}

