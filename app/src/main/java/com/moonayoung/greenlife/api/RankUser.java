package com.moonayoung.greenlife.api;

public class RankUser {
    private int weekChallengeCount;
    private String _id;
    private String nickname;

    public RankUser(int weekChallengeCount, String nickname) {
        this.weekChallengeCount = weekChallengeCount;
        this.nickname = nickname;
    }

    public RankUser(int weekChallengeCount, String _id, String nickname) {
        this.weekChallengeCount = weekChallengeCount;
        this._id = _id;
        this.nickname = nickname;
    }

    public int getWeekChallengeCount() {
        return weekChallengeCount;
    }

    public void setWeekChallengeCount(int weekChallengeCount) {
        this.weekChallengeCount = weekChallengeCount;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
