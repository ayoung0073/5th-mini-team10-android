package com.moonayoung.greenlife.rank;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moonayoung.greenlife.R;
import com.moonayoung.greenlife.api.RankUser;

import java.util.ArrayList;

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.ViewHolder> {

    ArrayList<RankUser> items = new ArrayList();
    onRankListClickListener listener;

    public void addItem(RankUser item) {
        items.add(item);
    }

    public void setItems(ArrayList<RankUser> items) { //ArrayList전체를 설정할 수 있는 함수
        this.items = items;
    }

    public RankUser getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, RankUser item) {
        items.set(position, item);
    }


    public int getItemCount() {
        return items.size();
    }

    public void setOnItemClickListener(onRankListClickListener listener) { //아이템뷰에 onClickListener 설정하기
        this.listener = listener;
    }


    @NonNull
    @Override
    public RankAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //getSystemService에서 참조하는 것과 동일한 코드
        View itemView = inflater.inflate(R.layout.rank_item, parent, false);

        return new RankAdapter.ViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RankAdapter.ViewHolder holder, int position) {
        //뷰홀더가 재사용될 때 호출되므로 뷰객체는 기존 것을 그대로 사용하고 데이터만 바꿔줌.
        RankUser item = items.get(position);
        holder.setItem(item);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView rankTextView; // rank_item 텍스트 뷰
        TextView nicknameTextView; // rank_item 텍스트 뷰
        TextView participateTextView; // rank_item 텍스트 뷰

        public ViewHolder(final View itemView, final onRankListClickListener listener) {
            super(itemView);

            rankTextView = itemView.findViewById(R.id.ranking_textView);
            nicknameTextView = itemView.findViewById(R.id.nickname_textView);
            participateTextView = itemView.findViewById(R.id.participate_textView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null) {
                        listener.onItemClick(RankAdapter.ViewHolder.this, view, position);
                    }
                }
            });
        }

        public void setItem(RankUser item){
            rankTextView.setText(" "+item.get_id());
            nicknameTextView.setText(" "+item.getNickname()+ " 님");
            participateTextView.setText(item.getWeekChallengeCount()+"회");

        }

    }

    public interface onRankListClickListener {
        public void onItemClick(RankAdapter.ViewHolder holder, View view, int position);
    }
}
