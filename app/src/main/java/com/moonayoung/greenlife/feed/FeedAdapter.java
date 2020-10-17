package com.moonayoung.greenlife.feed;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.moonayoung.greenlife.CustomDialog;
import com.moonayoung.greenlife.FeedDialog;
import com.moonayoung.greenlife.R;
import com.moonayoung.greenlife.api.Feed;
import com.moonayoung.greenlife.api.FeedItems;
import com.moonayoung.greenlife.api.Participate;
import com.moonayoung.greenlife.api.RetrofitClient;
import com.moonayoung.greenlife.intro.LoginFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

//    private ArrayList<FeedItem> listData = new ArrayList<>();
    onFeedListClickListener listener;
    List<FeedItems> items;
    Context rootFragment; //상위 프래그먼트의 문맥 얻어옴(Glide 사용하기 위해)


    public FeedAdapter(Context rootFragment) {
        this.rootFragment = rootFragment;
    }

    public void setOnItemClickListener(FeedAdapter.onFeedListClickListener listener) {
        this.listener = listener;
    }

    public void setItems(List<FeedItems> items) { //ArrayList전체를 설정할 수 있는 함수
        this.items = items;
    }

    @NonNull
    @Override
    public FeedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.feed_item, parent, false);

        return new FeedAdapter.ViewHolder(itemView, listener, rootFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FeedItems item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() { //아이템 총 개수
        return items.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView feedImage;
        private Context rootFragment;

        public ViewHolder(final View itemView, final onFeedListClickListener listener, Context rootFragment) {
            super(itemView);

            this.rootFragment  = rootFragment;
            feedImage = itemView.findViewById(R.id.feed_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                int position = getAdapterPosition();

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null) {
                        listener.onItemClick(FeedAdapter.ViewHolder.this, v, position);
                    }
                }
            });
        }

        public void setItem(final FeedItems item) {
            String httpAddress = "http://133.186.241.35:80/";
            final String imageUrl = httpAddress + item.getImageUrl();
            final String nickName = item.getUser().getNickname();
            Log.d("이미지", "" + imageUrl);
            Glide.with(rootFragment).load(imageUrl).into(feedImage);
            feedImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //커스텀 다이얼로그 사용용
                    FeedDialog customDialog = new FeedDialog(rootFragment, imageUrl, nickName);
                    customDialog.callFunction();
                }
            });
        }
    }

    public interface onFeedListClickListener {
        public void onItemClick(FeedAdapter.ViewHolder holder, View view, int position);
    }
}
