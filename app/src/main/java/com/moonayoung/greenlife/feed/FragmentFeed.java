package com.moonayoung.greenlife.feed;

        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.LinearLayout;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.fragment.app.Fragment;
        import androidx.fragment.app.FragmentActivity;
        import androidx.recyclerview.widget.GridLayoutManager;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import com.moonayoung.greenlife.R;
        import com.moonayoung.greenlife.api.Feed;
        import com.moonayoung.greenlife.api.LoginPost;
        import com.moonayoung.greenlife.api.RetrofitClient;
        import com.moonayoung.greenlife.api.User;
        import com.moonayoung.greenlife.rank.RankActivity;
        import com.moonayoung.greenlife.rank.RankAdapter;
        import com.moonayoung.greenlife.rank.RankData;

        import java.util.List;

        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;
        import retrofit2.Retrofit;

public class FragmentFeed extends Fragment {
    ImageView logoView;
    Button btn_rank;
    RecyclerView feedrecyclerView;
    GridLayoutManager layoutManager;
    boolean isSuccess;
    List<FeedItem> response_feed;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.fragment_feed,container,false);

        logoView = view.findViewById(R.id.logoView);
        btn_rank = view.findViewById(R.id.btn_rank);
        feedrecyclerView = view.findViewById(R.id.feed_RecyclerView);

        btn_rank.setOnClickListener(new View.OnClickListener() { //명예의 전당
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RankActivity.class);
                startActivity(intent);
            }
        });

        layoutManager = new GridLayoutManager(getActivity(), 3);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        feedrecyclerView.setLayoutManager(layoutManager);
        feedrecyclerView.setAdapter(new FeedAdapter());

        RetrofitClient.getApiService()
                .feedUpload("application/json; charset=utf-8").enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                if(response.isSuccessful()) { // 통신 성공
                    Feed feed = response.body();
                    Log.d("성공", "onRespnse: "+feed.isSuccess());

                } /*else { //통신 실패
                    Log.d("실패", );
                }*/
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) { //통신 실패
                Log.d("실패", "요청에 실패하였습니다");
            }

    });
        return view;
    }
}