package com.moonayoung.greenlife.feed;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moonayoung.greenlife.BuildConfig;
import com.moonayoung.greenlife.R;
import com.moonayoung.greenlife.api.Feed;
import com.moonayoung.greenlife.rank.RankAdapter;
import com.moonayoung.greenlife.rank.RankList;

import java.util.ArrayList;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {
    private ArrayList<FeedItem> listData = new ArrayList<>();
    onFeedListClickListener listener;

    public void setOnItemClickListener(FeedAdapter.onFeedListClickListener listener) {
        this.listener = listener;
    }


    @NonNull
    @Override
    public FeedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_feed, parent, false);
        return new FeedAdapter.ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FeedItem item = listData.get(position);
        holder.setItem(item);

    }

    @Override
    public int getItemCount() { //아이템 총 개수
        return listData.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView logoView;
        Button btn_rank;

        public ViewHolder(final View itemView, final onFeedListClickListener listener) {
            super(itemView);
            logoView = itemView.findViewById(R.id.feed_RecyclerView);
            btn_rank = itemView.findViewById(R.id.feed_RecyclerView);

            itemView.setOnClickListener(new View.OnClickListener() {
                int position = getAdapterPosition();

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null) {
                        listener.onItemClick(FeedAdapter.ViewHolder.this, v, position);
                    }
                }
            });
        }
        public void setItem(FeedItem item){

            logoView.setImageResource(item.getImageId());
            btn_rank.setText(item.getImageUrl());

        }
    }

    public interface onFeedListClickListener {
        public void onItemClick(FeedAdapter.ViewHolder holder, View view, int position);
    }
}
