package com.moonayoung.greenlife.challenge;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.moonayoung.greenlife.R;
import com.moonayoung.greenlife.api.ChallengeItem;
import com.moonayoung.greenlife.api.RetrofitClient;
import com.moonayoung.greenlife.camera.CameraActivity;
import com.moonayoung.greenlife.challenge.ChallengeList;

import java.util.ArrayList;
import java.util.List;


public class ChallengeAdapter extends RecyclerView.Adapter<ChallengeAdapter.ViewHolder> {

    List<ChallengeItem> items;
    onChallengeListClickListener listener;
    Context rootFragment; //상위 프래그먼트의 문맥 얻어옴(Glide 사용하기 위해)

    /*public void addItem(ChallengeList item) {
        items.add(item);
    }*/

    public ChallengeAdapter(Context rootFragment) {
        this.rootFragment = rootFragment;
    }

    public void setItems(List<ChallengeItem> items) { //ArrayList전체를 설정할 수 있는 함수
        this.items = items;
    }

    public ChallengeItem getItem(int position) {
        return items.get(position);
    }

    /*public void setItem(int position, ChallengeList item) {
        items.set(position, item);
    }*/


    public int getItemCount() {
        if(items!=null)
            return items.size();
        else
            return 0;
    }

    public void setOnItemClickListener(onChallengeListClickListener listener) { //아이템뷰에 onClickListener 설정하기
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.challenge_item, parent, false);

        return new ViewHolder(itemView, listener, rootFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //뷰홀더가 재사용될 때 호출되므로 뷰객체는 기존 것을 그대로 사용하고 데이터만 바꿔줌.
        ChallengeItem item = items.get(position);
        holder.setItem(item);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView; // challenge_item 텍스트 뷰
        ImageView imageView;
        private String imageUrl;
        String httpAddress;
        private Context rootFragment;
        Button joinBT; // challenge_item 참여 버튼


        public ViewHolder(final View itemView, final onChallengeListClickListener listener, Context rootFragment) {
            super(itemView);

            this.rootFragment = rootFragment;
            textView = itemView.findViewById(R.id.challenge_textView);
            imageView = itemView.findViewById(R.id.challenge_imageView);
            joinBT = itemView.findViewById(R.id.joinBT);

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

        public void setItem(ChallengeItem item) {
            httpAddress = "http://133.186.241.35:80/";
            imageUrl = httpAddress + item.getImageUrl();
            Log.d("이미지", "" + imageUrl);
            textView.setText(item.getTitle());
            Glide.with(rootFragment).load(imageUrl).into(imageView);
//            Glide.with(rootFragment).load(imageUrl)
//                    .placeholder(R.drawable.artboard_36)
//                    .error(R.drawable.login_)
//                    .into(imageView);


        }

    }

}
