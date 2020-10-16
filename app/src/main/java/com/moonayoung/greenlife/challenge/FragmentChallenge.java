package com.moonayoung.greenlife.challenge;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.collection.CircularArray;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.moonayoung.greenlife.CustomDialog;
import com.moonayoung.greenlife.MainActivity;
import com.moonayoung.greenlife.api.Challenge;
import com.moonayoung.greenlife.api.ChallengeItem;
import com.moonayoung.greenlife.api.Participate;
import com.moonayoung.greenlife.api.RetrofitClient;
import com.moonayoung.greenlife.api.SubChallenge;
import com.moonayoung.greenlife.api.SubChallengeItem;
import com.moonayoung.greenlife.camera.CameraActivity;
import com.moonayoung.greenlife.R;
import com.moonayoung.greenlife.intro.LoginFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//챌린지 주제 리스트 프래그먼트

public class FragmentChallenge extends Fragment {

    private static TextView detailChallenge_text;
    private static TextView shortCut_textView;
    private static List<ChallengeItem> response_challenges;
    private static SubChallenge response_subChallenge;
    private static List<SubChallengeItem> response_subChallengeItem;
    RecyclerView challengeListView;
    ChallengeAdapter adapter;
    FragmentManager fragmentManager = null;
    private Gson mGson;
    boolean response_success;
    ChallengeFragment1 challengeFragment1;
    ChallengeFragment2 challengeFragment2;
    ChallengeFragment3 challengeFragment3;
    ChallengeFragment4 challengeFragment4;
    ChallengeFragment5 challengeFragment5;
    static String subChallengeId;
    Bundle bundle;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_challenge, container, false);
        detailChallenge_text = rootView.findViewById(R.id.detail_challenge_text);
        final Button joinBT = rootView.findViewById(R.id.joinBT);
        shortCut_textView = rootView.findViewById(R.id.shortCut_textView);

        challengeListView = (RecyclerView) rootView.findViewById(R.id.challengListView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false); //linearlayout으로 리싸이클러뷰 설정
        challengeListView.setLayoutManager(layoutManager);

        if (LoginFragment.getToken() != null) {
            loginDone();
        }
        Call<Challenge> challenges = RetrofitClient.getApiService().getChallenges();
        challenges.enqueue(new Callback<Challenge>() {
            @Override
            public void onResponse(Call<Challenge> call, Response<Challenge> response) {
                if (response.isSuccessful()) {
                    Challenge challenge = response.body();
                    response_success = challenge.getSuccess();
                    response_challenges = challenge.getChallenge();
                    adapter = new ChallengeAdapter(getActivity());
                    adapter.setItems(response_challenges);
                    //adapter.setItems(challengeList.getChallengeLists()); // 데이터 저장되어 있음 Title, content, 세부 챌린지 배열
                    challengeListView.setAdapter(adapter); // 어댑터에 설정 -> 리싸이클러뷰에 챌린지 목록 보임
                    adapter.setOnItemClickListener(new onChallengeListClickListener() {
                        @Override
                        public void onItemClick(ChallengeAdapter.ViewHolder holder, View view, int position) { // 챌린지 목록 각각 눌렀을 때
                            Log.d("response_id", response_challenges.get(0).get_id());

                            ChallengeItem item = adapter.getItem(position);
                            position++;

                            FragmentManager fragmentManager = getFragmentManager();

                            switch (position) {
                                case 1: // 첫번째 click
                                    challengeFragment1 = new ChallengeFragment1();
                                    bundle = new Bundle();
                                    bundle.putString("response_id", "" + response_challenges.get(0).get_id());
                                    challengeFragment1.setArguments(bundle);
                                    fragmentManager.beginTransaction().replace(R.id.mainContainer, challengeFragment1).commit(); //1번째 챌린지 프래그먼트 띄움
                                    break;
                                case 2:
                                    challengeFragment2 = new ChallengeFragment2();
                                    bundle = new Bundle();
                                    bundle.putString("response_id", "" + response_challenges.get(1).get_id());
                                    challengeFragment2.setArguments(bundle);
                                    fragmentManager.beginTransaction().replace(R.id.mainContainer, challengeFragment2).commit();
                                    break;
                                case 3:
                                    challengeFragment3 = new ChallengeFragment3();
                                    bundle = new Bundle();
                                    bundle.putString("response_id", "" + response_challenges.get(2).get_id());
                                    challengeFragment3.setArguments(bundle);
                                    fragmentManager.beginTransaction().replace(R.id.mainContainer, challengeFragment3).commit();
                                    break;
                                case 4:
                                    challengeFragment4 = new ChallengeFragment4();
                                    bundle = new Bundle();
                                    bundle.putString("response_id", "" + response_challenges.get(3).get_id());
                                    challengeFragment4.setArguments(bundle);
                                    fragmentManager.beginTransaction().replace(R.id.mainContainer, challengeFragment4).commit();
                                    break;
                                case 5:
                                    challengeFragment5 = new ChallengeFragment5();
                                    bundle = new Bundle();
                                    bundle.putString("response_id", "" + response_challenges.get(4).get_id());
                                    challengeFragment5.setArguments(bundle);
                                    fragmentManager.beginTransaction().replace(R.id.mainContainer, challengeFragment5).commit();
                                    break;
                            }

                        }
                    });
                } else {
                    Log.d("연결X", "" + response.code());
                }
            }

            @Override
            public void onFailure(Call<Challenge> call, Throwable t) {
                Log.d("ChallengeFragment1_Test", "안됨되뫼");

            }
        });

        joinBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //팝업창
                RetrofitClient.getApiService()
                        .putData(LoginFragment.getToken(), subChallengeId)
                        .enqueue(new Callback<Participate>() {
                            @Override
                            public void onResponse(Call<Participate> call, Response<Participate> response) {
                                if (response.isSuccessful()) {
                                } else {
                                    Log.d("응답이상", "" + response.code());
                                }
                            }

                            @Override
                            public void onFailure(Call<Participate> call, Throwable t) {
                                Log.d("통신오류", "안됨되뫼");
                            }
                        });

                //커스텀 다이얼로그 사용용
               CustomDialog customDialog = new CustomDialog(getContext(), LoginFragment.getNickname());
                customDialog.callFunction();

                /*AlertDialog.Builder ad = new AlertDialog.Builder(getActivity(), R.style.MyAlertDialogStyle);
                ad.setTitle(LoginFragment.getNickname() + "님"); //username
                ad.setMessage("참여 감사합니다 :) \n 당신의 실천이 \n 일상이 되길 바랍니다.");
                ad.setIcon(R.drawable.alert);
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
                loginDone();*/

            }
        });
        return rootView;

    }

    public static void loginDone() {
        shortCut_textView.setText(LoginFragment.getNickname() + "님을 위한 챌린지 바로가기");
        //참여 횟수가 적은 챌린지를 추천 챌린지로 띄워주기
        for (int i = 0; i < response_challenges.size(); i++) {
            Call<SubChallenge> subChallenge = RetrofitClient.getApiService().getDetatilChallenges(LoginFragment.getToken(), response_challenges.get(i).get_id());
            subChallenge.enqueue(new Callback<SubChallenge>() {
                @Override
                public void onResponse(Call<SubChallenge> call, Response<SubChallenge> response) {
                    if (response.isSuccessful()) {
                        response_subChallenge = response.body();
                        response_subChallengeItem = response_subChallenge.getSubchallenges();
                        loop:
                        for (int j = 0; ; j++) {
                            for (int x = 0; x < response_subChallengeItem.size(); x++) {
                                if (response_subChallengeItem.get(x).getParticipate() == j) {
                                    detailChallenge_text.setText(response_subChallengeItem.get(x).getTitle());
                                    subChallengeId = response_subChallengeItem.get(x).get_id();
                                }
                                if (detailChallenge_text.getText() != "") {
                                    break loop;
                                }
                            }
                        }
                    } else {
                        Log.d("연결X", "" + response.code());
                    }
                }

                @Override
                public void onFailure(Call<SubChallenge> call, Throwable t) {
                    Log.d("ChallengeFragment1_Test", "안됨되뫼");

                }
            });
            if (detailChallenge_text.getText() != "") {
                break;
            }
        }
    }
}

