package com.moonayoung.greenlife.challenge;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moonayoung.greenlife.R;
import com.moonayoung.greenlife.api.RetrofitClient;
import com.moonayoung.greenlife.api.SubChallenge;
import com.moonayoung.greenlife.api.SubChallengeItem;
import com.moonayoung.greenlife.challenge.ChallengeFragment1;
import com.moonayoung.greenlife.challenge.ChallengeFragment2;
import com.moonayoung.greenlife.challenge.ChallengeFragment3;
import com.moonayoung.greenlife.challenge.ChallengeList;
import com.moonayoung.greenlife.challenge.DetailChallengeAdapter;
import com.moonayoung.greenlife.intro.LoginFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//주제4 눌렀을 때의 프래그먼트

public class ChallengeFragment4 extends Fragment {

    RecyclerView detailchallengeListView;
    DetailChallengeAdapter adapter;
    FragmentChallenge fragmentChallenge = new FragmentChallenge();
    LoginFragment loginFragment = new LoginFragment();
    SubChallenge subChallenge;
    String challengeId;
    String token;
    String response_title;
    String response_imageUrl;
    String response_text;
    Bundle bundle = getArguments();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_challenge1,container,false);

        if(bundle != null){
            challengeId = bundle.getString("response_id"); //Name 받기.
        }
        token = loginFragment.getToken();

        Call<SubChallenge> subChallenges = RetrofitClient.getApiService()
                .getDetatilChallenges(challengeId, token);
        subChallenges.enqueue(new Callback<SubChallenge>() {
            @Override
            public void onResponse(Call<SubChallenge> call, Response<SubChallenge> response) {
                if (response.isSuccessful()) {
                    subChallenge = response.body();
                    response_title = subChallenge.getTitle(); //주제 제목
                    response_imageUrl = subChallenge.getImageUrl(); //주제 이미지
                    response_text = subChallenge.getText(); //주제 소개? 멘트

                    Log.d("아이디",challengeId);
                    Log.d("토큰",token);

                    TextView content = rootView.findViewById(R.id.content1);
                    content.setText(response_text); //주제 소개 문구

                    detailchallengeListView = (RecyclerView)rootView.findViewById(R.id.detail_challengeListView);
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
