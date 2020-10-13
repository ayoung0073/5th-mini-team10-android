package com.moonayoung.greenlife.rank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.moonayoung.greenlife.R;
import com.moonayoung.greenlife.api.Rank;
import com.moonayoung.greenlife.api.RankUser;
import com.moonayoung.greenlife.api.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RankActivity extends AppCompatActivity {

    RecyclerView rankListView;
    RankAdapter adapter;
    FragmentManager fragmentManager = null;
    Rank rank;
    ArrayList<RankUser> users;
    int weekChallengeCount;
    String _id;
    String nickname;
    RankUser user;
    RankActivity me = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        users  = new ArrayList<>();
        rankListView = (RecyclerView)findViewById(R.id.rank_RecyclerView);

        RetrofitClient.getApiService().getRank("application/json; charset=utf-8").enqueue(new Callback<Rank>() {
            @Override
            public void onResponse(Call<Rank> call, Response<Rank> response) {
                rank = response.body();
                users = new ArrayList<RankUser>(); // 이거 안하면..?
                Log.d("Rank", "success? -> "+rank.isSuccess());
                Log.d("Rank", "user 수" + rank.getUser().size());
                //users = rank.getUser();


                for(int i = 0; i<rank.getUser().size();i++){
                    weekChallengeCount = rank.getUser().get(i).getWeekChallengeCount();
                    //_id = rank.getUser().get(i).get_id();
                    nickname = rank.getUser().get(i).getNickname();
                    user = new RankUser(weekChallengeCount, ""+Integer.toString(i+1),nickname);
                    users.add(user);
                }
                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false); //linearlayout으로 리싸이클러뷰 설정
                rankListView.setLayoutManager(layoutManager);
                adapter = new RankAdapter();
                adapter.setItems(users);
                rankListView.setAdapter(adapter); // 어댑터에 설정 -> 리싸이클러뷰에 랭킹 보임
            }

            @Override
            public void onFailure(Call<Rank> call, Throwable t) {
                Log.d("Rank", "failure ㅠㅠ");
            }
        });


        Button backBT = findViewById(R.id.backBT);
        backBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                me.finish();
            }
        });
    }
}