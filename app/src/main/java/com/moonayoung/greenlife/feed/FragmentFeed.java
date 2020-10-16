package com.moonayoung.greenlife.feed;

        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.ImageView;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.fragment.app.Fragment;
        import androidx.recyclerview.widget.GridLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;
        import androidx.recyclerview.widget.StaggeredGridLayoutManager;

        import com.moonayoung.greenlife.R;
        import com.moonayoung.greenlife.api.Feed;
        import com.moonayoung.greenlife.api.FeedItems;
        import com.moonayoung.greenlife.api.RetrofitClient;
        import com.moonayoung.greenlife.rank.RankActivity;

        import java.util.List;

        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;

public class FragmentFeed extends Fragment {
    ImageView logoView;
    Button btn_rank;
    RecyclerView feedrecyclerView;
    GridLayoutManager layoutManager;
    boolean isSuccess;
    Feed response_feed;
    List<FeedItems> response_feedList;
    FeedAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.fragment_feed,container,false);

//        logoView = view.findViewById(R.id.logoView);
        btn_rank = view.findViewById(R.id.btn_rank);
        feedrecyclerView = (RecyclerView)view.findViewById(R.id.feed_RecyclerView);
        layoutManager = new GridLayoutManager(getActivity(), 3);
        feedrecyclerView.setLayoutManager(layoutManager);

        btn_rank.setOnClickListener(new View.OnClickListener() { //명예의 전당
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RankActivity.class);
                startActivity(intent);
            }
        });

        RetrofitClient.getApiService()
                .feedUpload()
                .enqueue(new Callback<Feed>() {
                    @Override
                    public void onResponse(Call<Feed> call, Response<Feed> response) {
                        if (response.isSuccessful()) {
                            response_feed = response.body();
                            response_feedList = response_feed.getFeed();
                            Log.d("피드통신", "성공");
                            Log.d("이미지유알엘",response_feedList.get(0).getImageUrl());
                            adapter = new FeedAdapter(getActivity());
                            adapter.setItems(response_feedList);
                            feedrecyclerView.setAdapter(adapter);
                        } else {
                            Log.d("응답이상", "" + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<Feed> call, Throwable t) {
                        Log.d("통신오류", "안됨되뫼");
                    }
                });

        return view;
    }

}