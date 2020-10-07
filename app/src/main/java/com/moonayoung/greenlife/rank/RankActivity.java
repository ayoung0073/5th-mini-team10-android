package com.moonayoung.greenlife.rank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ViewGroup;

import com.moonayoung.greenlife.R;

public class RankActivity extends AppCompatActivity {

    RecyclerView rankListView;
    RankAdapter adapter;
    FragmentManager fragmentManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        rankListView = (RecyclerView)findViewById(R.id.rank_RecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false); //linearlayout으로 리싸이클러뷰 설정
        rankListView.setLayoutManager(layoutManager);
        RankData data = new RankData();
        adapter = new RankAdapter();
        adapter.setItems(data.rankLists);
        rankListView.setAdapter(adapter); // 어댑터에 설정 -> 리싸이클러뷰에 랭킹 보임
    }
}