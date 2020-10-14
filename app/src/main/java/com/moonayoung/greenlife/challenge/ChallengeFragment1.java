package com.moonayoung.greenlife.challenge;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.JsonArray;
import com.moonayoung.greenlife.api.ApiService;
import com.moonayoung.greenlife.api.RetrofitClient;
import com.moonayoung.greenlife.api.SubChallenge;
import com.moonayoung.greenlife.api.SubChallengeItem;
import com.moonayoung.greenlife.camera.CameraActivity;
import com.moonayoung.greenlife.R;
import com.moonayoung.greenlife.intro.LoginFragment;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

//세부챌린지 리스트 프래그먼트

public class ChallengeFragment1 extends Fragment {

    RecyclerView detailchallengeListView;
    DetailChallengeAdapter adapter;
    SubChallenge subChallenge;
    String challengeId;
    String token;
    String response_title;
    String response_imageUrl;
    String response_text;
    String httpAddress;
    String imageUrl;
    ChallengeFragment1 me = this;
//    Bundle bundle = getArguments();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_challenge1, container, false);

        if(getArguments().getString("response_id") != null){
            challengeId = getArguments().getString("response_id"); //Name 받기.
        } else{
            Log.d("번들","비어있음");
        }
        token = LoginFragment.getToken();

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

        RetrofitClient.getApiService()
                .getDetatilChallenges(token, challengeId)
                .enqueue(new Callback<SubChallenge>() {
            @Override
            public void onResponse(Call<SubChallenge> call, Response<SubChallenge> response) {
                if (response.isSuccessful()) {
                    subChallenge = response.body();
                    response_title = subChallenge.getTitle(); //주제 제목
                    response_imageUrl = subChallenge.getImageUrl(); //주제 이미지
                    response_text = subChallenge.getText(); //주제 소개? 멘트

                    Log.d("세부통신","통신성공");
                    Log.d("아이디",challengeId);
                    Log.d("토큰",token);
                    Log.d("주제",""+subChallenge.getSubchallenges().size());
                    Log.d("세부아이디잉",subChallenge.getSubchallenges().get(0).get_id());
                    TextView content = rootView.findViewById(R.id.content1);
                    TextView challenge= rootView.findViewById(R.id.challenge_textView);
                    ImageView imageView = rootView.findViewById(R.id.imageView);

                    httpAddress = "http://133.186.241.35:80/";
                    imageUrl = httpAddress + response_imageUrl;
                    content.setText(response_text); //주제 소개 문구
                    challenge.setText(response_title);
                    Glide.with(getActivity()).load(imageUrl).into(imageView);

                    detailchallengeListView = (RecyclerView) rootView.findViewById(R.id.detail_challengeListView);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false); //linearlayout으로 리싸이클러뷰 설정
                    detailchallengeListView.setLayoutManager(layoutManager);
                    adapter = new DetailChallengeAdapter(getActivity()); //디테일챌린지어댑터 클래스에 문맥 보냄(현재 프래그먼트에 팝업 띄우기 위해)
                    adapter.setChallengeList(subChallenge.getSubchallenges());
                    //adapter.setItems(challengeList.getChallengeLists()); // 데이터 저장되어 있음 Title, content, 세부 챌린지 배열
                    detailchallengeListView.setAdapter(adapter); // 어댑터에 설정 -> 리싸이클러뷰에 챌린지 목록 보임

                } else {
                    Log.d("응답이상", "" + response.code());
                }
            }

            @Override
            public void onFailure(Call<SubChallenge> call, Throwable t) {
                Log.d("통신오류", "안됨되뫼");
            }
        });


        return rootView;
    }

}
