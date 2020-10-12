/*
package com.moonayoung.greenlife.challenge;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.moonayoung.greenlife.api.Challenge;
import com.moonayoung.greenlife.api.ChallengeItem;
import com.moonayoung.greenlife.api.RetrofitClient;
import com.moonayoung.greenlife.api.SubChallenge;
import com.moonayoung.greenlife.camera.CameraActivity;
import com.moonayoung.greenlife.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//챌린지 주제 리스트 프래그먼트

public class FragmentChallenge extends Fragment {

    RecyclerView challengeListView;
    ChallengeAdapter adapter;
    FragmentManager fragmentManager = null;
    private Gson mGson;
    boolean response_success;
    List<ChallengeItem> response_challenges;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_challenge, container, false);
        challengeListView = (RecyclerView)rootView.findViewById(R.id.challengListView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false); //linearlayout으로 리싸이클러뷰 설정
        challengeListView.setLayoutManager(layoutManager);
        //ChallengeList challengeList = new ChallengeList(); // ChallengeData로 바꿈
        //ChallengeData data = new ChallengeData();

        final Call<Challenge> challenges = RetrofitClient.getApiService().getChallenges();
        challenges.enqueue(new Callback<Challenge>() {
            @Override
            public void onResponse(Call<Challenge> call, Response<Challenge> response) {
                if(response.isSuccessful()){
                    Challenge challenge = response.body();
                    response_success = challenge.getSuccess();
                    response_challenges = challenge.getChallenge();
//                    Log.d("이잉",response_challenges.get(0).getTitle());
//                    Log.d("오잉","glgl");
                        Log.d("히히",response_challenges.get(0).getTitle());
                    adapter = new ChallengeAdapter();
                    adapter.setItems(response_challenges);
                    //adapter.setItems(challengeList.getChallengeLists()); // 데이터 저장되어 있음 Title, content, 세부 챌린지 배열
                    challengeListView.setAdapter(adapter); // 어댑터에 설정 -> 리싸이클러뷰에 챌린지 목록 보임
                } else{
                    Log.d("연결X",""+response.code());
                }
            }

            @Override
            public void onFailure(Call<Challenge> call, Throwable t) {
                Log.d("ChallengeFragment1_Test","안됨되뫼");

            }
        });

        Button joinBT = rootView.findViewById(R.id.joinBT);

        joinBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //팝업창
                AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
                ad.setTitle("님"); //username
                ad.setMessage("참여 감사합니다 :) \n 당신의 실천이 \n 일상이 되길 바랍니다.");
                ad.setPositiveButton("사진으로 인증하기",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(getActivity(), CameraActivity.class);
                                startActivity(intent);
                                dialogInterface.dismiss();
                            }
                        }).setNegativeButton("인증은 안 할래요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                ad.show();
            }
        });

        */
/*adapter.setOnItemClickListener(new onChallengeListClickListener() {
            @Override
            public void onItemClick(ChallengeAdapter.ViewHolder holder, View view, int position) { // 챌린지 목록 각각 눌렀을 때

                //fragmentStack.push(currentFragment); //프래그먼트 바뀌기 전에 현재 프래그먼트 스택에 저장

                //ChallengeList item = adapter.getItem(position);
                position++;
                //Toast.makeText(getApplicationContext(), position + "번째 목록 선택됨",Toast.LENGTH_LONG).show();
                fragmentManager = getFragmentManager();

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
        });*//*

        return rootView;

    }


}
*/

package com.moonayoung.greenlife.challenge;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moonayoung.greenlife.camera.CameraActivity;
import com.moonayoung.greenlife.R;

import java.util.Stack;

//챌린지 주제 리스트 프래그먼트

public class FragmentChallenge extends Fragment {

    public static Stack<Fragment> fragmentStack; //프래그먼트 뒤로가기를 위한 프래그먼트 스택
    RecyclerView challengeListView;
    ChallengeAdapter adapter;
    FragmentManager fragmentManager = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final FragmentChallenge currentFragment = this; //현재 프래그먼트
        ChallengeFragment1 challengeFragment1 = new ChallengeFragment1(); //이동할 프래그먼트1
        ChallengeFragment2 challengeFragment2 = new ChallengeFragment2(); //이동할 프래그먼트2
        ChallengeFragment3 challengeFragment3 = new ChallengeFragment3(); //이동할 프래그먼트3
//        ChallengeFragment4 challengeFragment4 = new ChallengeFragment4(); //이동할 프래그먼트4
//        ChallengeFragment5 challengeFragment5 = new ChallengeFragment5(); //이동할 프래그먼트5

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
            public void onClick(View view) { //팝업창
                AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
                ad.setTitle("님"); //username
                ad.setMessage("참여 감사합니다 :) \n 당신의 실천이 \n 일상이 되길 바랍니다.");
                ad.setPositiveButton("사진으로 인증하기",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(getActivity(), CameraActivity.class);
                                startActivity(intent);
                                dialogInterface.dismiss();
                            }
                        }).setNegativeButton("인증은 안 할래요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                ad.show();
            }
        });

        adapter.setOnItemClickListener(new onChallengeListClickListener() {
            @Override
            public void onItemClick(ChallengeAdapter.ViewHolder holder, View view, int position) { // 챌린지 목록 각각 눌렀을 때

                //fragmentStack.push(currentFragment); //프래그먼트 바뀌기 전에 현재 프래그먼트 스택에 저장

                ChallengeList item = adapter.getItem(position);
                position++;
                //Toast.makeText(getApplicationContext(), position + "번째 목록 선택됨",Toast.LENGTH_LONG).show();
                fragmentManager = getFragmentManager();

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
                        //fragmentManager.beginTransaction().replace(R.id.mainContainer, new ChallengeFragment4()).commit();
                        break;
                    case 5:
                        //fragmentManager.beginTransaction().replace(R.id.mainContainer, new ChallengeFragment5()).commit();
                        break;
                }

            }
        });
        return rootView;

    }


}