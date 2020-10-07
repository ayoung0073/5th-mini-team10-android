package com.moonayoung.greenlife.api;

public class SubChallenge {
    int subChallengeId;
    String challengeTitle;
    int participate;

    public int getSubChallengeId() {
        return subChallengeId;
    }

    public void setSubChallengeId(int subChallengeId) {
        this.subChallengeId = subChallengeId;
    }

    public String getChallengeTitle() {
        return challengeTitle;
    }

    public void setChallengeTitle(String challengeTitle) {
        this.challengeTitle = challengeTitle;
    }

    public int getParticipate() {
        return participate;
    }

    public void setParticipate(int participate) {
        this.participate = participate;
    }
}
