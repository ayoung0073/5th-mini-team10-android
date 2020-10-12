package com.moonayoung.greenlife.challenge;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moonayoung.greenlife.R;
import com.moonayoung.greenlife.api.ChallengeItem;
import com.moonayoung.greenlife.challenge.ChallengeList;

import java.util.ArrayList;
import java.util.List;


public class ChallengeAdapter extends RecyclerView.Adapter<ChallengeAdapter.ViewHolder> {

    //ArrayList<ChallengeList> items = new ArrayList();
    List<ChallengeItem> items;
    onChallengeListClickListener listener;

    /*public void addItem(ChallengeList item) {
        items.add(item);
    }*/

    public void setItems(List<ChallengeItem> items) { //ArrayList전체를 설정할 수 있는 함수
        this.items = items;
    }

    /*public ChallengeList getItem(int position) {
        return items.get(position);
    }*/

    /*public void setItem(int position, ChallengeList item) {
        items.set(position, item);
    }*/


    public int getItemCount() {
        return items.size();
    }

    public void setOnItemClickListener(onChallengeListClickListener listener) { //아이템뷰에 onClickListener 설정하기
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //getSystemService에서 참조하는 것과 동일한 코드
        View itemView = inflater.inflate(R.layout.challenge_item, parent, false);

        return new ViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //뷰홀더가 재사용될 때 호출되므로 뷰객체는 기존 것을 그대로 사용하고 데이터만 바꿔줌.
        ChallengeItem item = items.get(position);
        holder.setItem(item);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView; // challenge_item 텍스트 뷰

        public ViewHolder(final View itemView, final onChallengeListClickListener listener) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null) {
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });
        }

        public void setItem(ChallengeItem item){
            textView.setText(item.getTitle());
        }

    }
}
