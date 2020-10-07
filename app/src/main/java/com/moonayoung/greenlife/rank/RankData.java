package com.moonayoung.greenlife.rank;

import com.moonayoung.greenlife.challenge.ChallengeList;

import java.util.ArrayList;

public class RankData {

    ArrayList<RankList> rankLists = new ArrayList();

    public RankData() {
        rankLists.add(new RankList(1, "이승희짱짱짱",153));
        rankLists.add(new RankList(2, "최서윤짱짱짱",121));
        rankLists.add(new RankList(3, "최서윤최고",95));
        rankLists.add(new RankList(4, "김현지최고",84));
        rankLists.add(new RankList(5, "김효리짱짱",62));
        rankLists.add(new RankList(6, "김효리짱짱",57));
    }
}
