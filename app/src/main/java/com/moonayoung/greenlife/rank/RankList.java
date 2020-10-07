package com.moonayoung.greenlife.rank;

public class RankList {
    int ranking;
    String nickname;
    int participate;

    public RankList(){

    }
//    public RankList(String nickname) {
//        this.nickname = nickname;
//    }

    public RankList(int ranking, String nickname, int participate) {
        this.ranking = ranking;
        this.nickname = nickname;
        this.participate = participate;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getParticipate() {
        return participate;
    }

    public void setParticipate(int participate) {
        this.participate = participate;
    }
}
