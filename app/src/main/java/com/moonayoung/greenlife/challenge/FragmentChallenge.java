package com.moonayoung.greenlife.challenge;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moonayoung.greenlife.CameraActivity;
import com.moonayoung.greenlife.R;

//챌린지 주제 리스트 프래그먼트

public class FragmentChallenge extends Fragment {

    RecyclerView challengeListView;
    ChallengeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_challenge, container, false);
        challengeListView = (RecyclerView)rootView.findViewById(R.id.challengListView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false); //linearlayout으로 리싸이클러뷰 설정
        challengeListView.setLayoutManager(layoutManager);
        //ChallengeList challengeList = new ChallengeList(); // ChallengeData로 바꿈
        ChallengeData data = new ChallengeData();
        adapter = new ChallengeAdapter();
        adapter.setItems(data.challengeLists);
        //adapter.setItems(challengeList.getChallengeLists()); // 데이터 저장되어 있음 Title, content, 세부 챌린지 배열
        challengeListView.setAdapter(adapter); // 어댑터에 설정 -> 리싸이클러뷰에 챌린지 목록 보임

        Button joinBT = rootView.findViewById(R.id.joinBT);
        joinBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),CameraActivity.class);
                getActivity().startActivityForResult(intent, 101);
            }
        });

        adapter.setOnItemClickListener(new onChallengeListClickListener() {
            @Override
            public void onItemClick(ChallengeAdapter.ViewHolder holder, View view, int position) { // 챌린지 목록 각각 눌렀을 때
                ChallengeList item = adapter.getItem(position);
                position++;
                //Toast.makeText(getApplicationContext(), position + "번째 목록 선택됨",Toast.LENGTH_LONG).show();

                FragmentManager fragmentManager = getFragmentManager();
                switch (position) {
                    case 1: // 첫번째 click
                        fragmentManager.beginTransaction().replace(R.id.mainContainer, new ChallengeFragment1()).commit(); //1번째 챌린지 프래그먼트 띄움
                        break;
                    case 2:
                        fragmentManager.beginTransaction().replace(R.id.mainContainer, new ChallengeFragment2()).commit();
                        break;
                    case 3:
                        fragmentManager.beginTransaction().replace(R.id.mainContainer, new ChallengeFragment3()).commit();
                        break;
                    case 4:
                        fragmentManager.beginTransaction().replace(R.id.mainContainer, new ChallengeFragment4()).commit();
                        break;
                    case 5:
                        fragmentManager.beginTransaction().replace(R.id.mainContainer, new ChallengeFragment5()).commit();
                        break;
                }

            }
        });
        return rootView;

    }
}