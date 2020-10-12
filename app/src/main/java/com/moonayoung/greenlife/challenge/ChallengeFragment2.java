package com.moonayoung.greenlife.challenge;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moonayoung.greenlife.R;
import com.moonayoung.greenlife.camera.CameraActivity;
import com.moonayoung.greenlife.challenge.ChallengeData;
import com.moonayoung.greenlife.challenge.ChallengeFragment1;
import com.moonayoung.greenlife.challenge.ChallengeList;
import com.moonayoung.greenlife.challenge.DetailChallengeAdapter;

//주제2 눌렀을 때의 프래그먼트

public class ChallengeFragment2 extends Fragment {

    ChallengeData data = new ChallengeData();
    ChallengeList selectedchallengeList = new ChallengeList(); //선택된 주제의 챌린지리스트
    RecyclerView detailchallengeListView;
    DetailChallengeAdapter adapter;

    ChallengeFragment2 me = this;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_challenge1,container,false);
        TextView content = rootView.findViewById(R.id.content1);
        content.setText(data.challengeLists.get(1).getContent());

        Button backBT = rootView.findViewById(R.id.backBT); // 1012 백버튼
        backBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    getActivity().getSupportFragmentManager().beginTransaction().remove(me).commit();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });

        detailchallengeListView = (RecyclerView)rootView.findViewById(R.id.detail_challengeListView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false); //linearlayout으로 리싸이클러뷰 설정
        detailchallengeListView.setLayoutManager(layoutManager);
        //ChallengeList challengeList = new ChallengeList(); // ChallengeData로 바꿈
        ChallengeData data = new ChallengeData();
        selectedchallengeList = data.challengeLists.get(1);
        adapter = new DetailChallengeAdapter(getActivity()); //디테일챌린지어댑터 클래스에 문맥 보냄(현재 프래그먼트에 팝업 띄우기 위해)
        adapter.setChallengeList(selectedchallengeList);
        //adapter.setItems(challengeList.getChallengeLists()); // 데이터 저장되어 있음 Title, content, 세부 챌린지 배열
        detailchallengeListView.setAdapter(adapter); // 어댑터에 설정 -> 리싸이클러뷰에 챌린지 목록 보임
/*
        Button joinBT = rootView.findViewById(R.id.joinBT);
        joinBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CameraActivity.class);
                getActivity().startActivityForResult(intent, 101);
            }
        });*/

//        adapter.setOnItemClickListener(new DetailChallengeAdapter.onDetailChallengeListClickListener() {
//            @Override
//            public void onItemClick(DetailChallengeAdapter.ViewHolder holder, View view, int position) { //세부 챌린지 목록 각각 눌렀을 때
//                ChallengeList item = adapter.getItem(position);
//                position++;
//                //Toast.makeText(getApplicationContext(), position + "번째 목록 선택됨",Toast.LENGTH_LONG).show();
//
//                FragmentManager fragmentManager = getFragmentManager();
//                switch (position) {
//                    case 1: // 첫번째 click
//                        fragmentManager.beginTransaction().replace(R.id.mainContainer, new ChallengeFragment1()).commit(); //1번째 챌린지 프래그먼트 띄움
//                        break;
//                    case 2:
//                        fragmentManager.beginTransaction().replace(R.id.mainContainer, new ChallengeFragment2()).commit();
//                        break;
//                    case 3:
//                        fragmentManager.beginTransaction().replace(R.id.mainContainer, new ChallengeFragment3()).commit();
//                        break;
//                    case 4:
//                        fragmentManager.beginTransaction().replace(R.id.mainContainer, new ChallengeFragment4()).commit();
//                        break;
//                    case 5:
//                        //fragmentManager.beginTransaction().replace(R.id.mainContainer, new ChallengeFragment5()).commit();
//                        break;
//                }
//            }
//
//        });
        return rootView;
    }

}
