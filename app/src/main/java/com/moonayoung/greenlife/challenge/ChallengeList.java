package com.moonayoung.greenlife.challenge;

import java.util.ArrayList;

public class ChallengeList {
    ArrayList<ChallengeList> challengeLists = new ArrayList();
    String title; // 챌린지 목록에 나타날 대표 글
    String content; // 짧은 뉴스 기사, 혹은 설명글, 인용글
    String[] challenge; // 세부 챌린지 나열

    public ChallengeList(){

    }
    public ChallengeList(String title) {
        this.title = title;
    }

    public ChallengeList(String title, String content, String[] challenge) {
        this.title = title;
        this.content = content;
        this.challenge = challenge;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getChallenge() {
        return challenge;
    }

    public void setChallenge(String[] challenge) {
        this.challenge = challenge;
    }
}
