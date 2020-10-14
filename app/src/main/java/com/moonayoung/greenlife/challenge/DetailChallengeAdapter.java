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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.moonayoung.greenlife.R;
import com.moonayoung.greenlife.api.ChallengeItem;
import com.moonayoung.greenlife.api.Participate;
import com.moonayoung.greenlife.api.RetrofitClient;
import com.moonayoung.greenlife.api.SubChallenge;
import com.moonayoung.greenlife.api.SubChallengeItem;
import com.moonayoung.greenlife.camera.CameraActivity;
import com.moonayoung.greenlife.intro.LoginFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailChallengeAdapter extends RecyclerView.Adapter<DetailChallengeAdapter.ViewHolder> {

    Context rootFragment; //상위 프래그먼트의 문맥 얻어옴(거기에 팝업 띄우기 위해)
    onDetailChallengeListClickListener listener;
    static Participate response_participate;
    static List<SubChallengeItem> items;
    static int participateCount;
    static String subChallengeId;
    static String token;

    public DetailChallengeAdapter(Context rootFragment){
        this.rootFragment = rootFragment;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.detail_challenge_item,parent,false);

        token = LoginFragment.getToken();
        return new ViewHolder(itemView,listener,rootFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SubChallengeItem item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public void setOnItemClickListener(onDetailChallengeListClickListener listener) { //아이템뷰에 onClickListener 설정하기
        this.listener = listener;
    }

    //선택한 주제의 챌린지 리스트 가져오기
    public void setChallengeList(List<SubChallengeItem> list){
        this.items = list;
    }

    public interface onDetailChallengeListClickListener {
        public void onItemClick(DetailChallengeAdapter.ViewHolder holder, View view, int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView; // detail_challenge_item 텍스트 뷰
        Button joinBT; // detail_challenge_item 참여 버튼
        private Context rootFragment;

        public ViewHolder(final View itemView, final onDetailChallengeListClickListener listener,Context rootFragment) {
            super(itemView);

            this.rootFragment = rootFragment;
            textView = itemView.findViewById(R.id.detail_challenge_text);
            joinBT = itemView.findViewById(R.id.joinBT);

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

        public void setItem(final SubChallengeItem item) {

            textView.setText(item.getTitle());
            joinBT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    subChallengeId = item.get_id();
//                    Log.d("세부아이디잉1",subChallengeId);
//                    Log.d("세부타이틀1",item.getTitle());

//                        Log.d("아이디잉",subChallengeId);
//                    Log.d("버튼 ",LoginFragment.getToken());
//                    Log.d("토크은",LoginFragment.getToken());

                    RetrofitClient.getApiService()
                            .putData(token, subChallengeId)
                            .enqueue(new Callback<Participate>() {
                        @Override
                        public void onResponse(Call<Participate> call, Response<Participate> response) {
                            if (response.isSuccessful()) {
                                response_participate = response.body();
                                participateCount = response_participate.getCount(); //주제 제목
//                                Log.d("통신","성공");
                                Log.d("참여",""+participateCount);
//                                Log.d("세부챌린지아이디",subChallengeId);

                            } else {
                                Log.d("응답이상", "" + response.code());
                            }
                        }

                        @Override
                        public void onFailure(Call<Participate> call, Throwable t) {
                            Log.d("통신오류", "안됨되뫼");
                        }
                    });

                    //팝업창
                    AlertDialog.Builder ad = new AlertDialog.Builder(rootFragment);
                    ad.setTitle(LoginFragment.getNickname()+"님"); //usㄺㅁername
                    ad.setMessage("참여 감사합니다 :) \n 당신의 실천이 \n 일상이 되길 바랍니다.");
                    ad.setPositiveButton("사진으로 인증하기",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(rootFragment, CameraActivity.class);
                                    rootFragment.startActivity(intent);
                                    dialogInterface.dismiss();
                                }
                            }).setNegativeButton("인증은 안 할래요", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    ad.show();
                }
            });
        }

    }
}


