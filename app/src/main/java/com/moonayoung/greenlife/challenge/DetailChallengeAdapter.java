package com.moonayoung.greenlife.challenge;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moonayoung.greenlife.R;

import java.util.ArrayList;

public class DetailChallengeAdapter extends RecyclerView.Adapter<DetailChallengeAdapter.ViewHolder> {

    onDetailChallengeListClickListener listener;
    ChallengeList selectedchallengeList = new ChallengeList();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.detail_challenge_item,parent,false);

        return new ViewHolder(itemView,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setItem(selectedchallengeList.getChallenge()[position]);
    }

    @Override
    public int getItemCount() {
        return selectedchallengeList.challenge.length;
    }
    public void setOnItemClickListener(onDetailChallengeListClickListener listener) { //아이템뷰에 onClickListener 설정하기
        this.listener = listener;
    }

    //선택한 주제의 챌린지 리스트 가져오기
    public void setChallengeList(ChallengeList list){
        this.selectedchallengeList = list;
    }

    public interface onDetailChallengeListClickListener {
        public void onItemClick(DetailChallengeAdapter.ViewHolder holder, View view, int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView; // detail_challenge_item 텍스트 뷰

        public ViewHolder(final View itemView, final onDetailChallengeListClickListener listener) {
            super(itemView);

            textView = itemView.findViewById(R.id.detail_challenge_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null) {
                        listener.onItemClick(DetailChallengeAdapter.ViewHolder.this, view, position);
                    }
                }
            });
        }

        public void setItem(String item) {
            textView.setText(item);
        }

    }
}
