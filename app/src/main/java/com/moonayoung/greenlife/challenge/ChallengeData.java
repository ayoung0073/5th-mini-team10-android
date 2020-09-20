package com.moonayoung.greenlife.challenge;

import java.util.ArrayList;

class ChallengeData {

    ArrayList<ChallengeList> challengeLists = new ArrayList();

    public ChallengeData(){
        challengeLists.add(new ChallengeList("콸콸, 쓰는 물이 많으면 졸졸, 먹는 물이 부족해져요.","지구 전체의 물 중 1%만이 먹을 수 있는 물이랍니다.\n" +
                "소중한 물을 아껴주세요.\n", new String[]{"양치 시 양치 컵을 사용하고 수도꼭지를 잠가 놓는 실천을 해봐요.","빨래는 한 번에 모아서 빨아볼까요?"}));

        challengeLists.add(new ChallengeList("녹고있는 빙하 위 북극곰들을 구해주세요!","북극곰은 바다 얼음 위에서 먹잇감을 사냥하는데 이 바다 얼음이 줄면 먹잇감을 찾아 헤매야하며, 결국은 식량 부족으로 새끼도 기를 수 없게 돼요. 이대로라면 2100년에는 북극곰을 지구상에서 볼 수 없어요.  소중한 생명을 끊지 말아주세요." , new String[]{"차 대신 자전거에 올라타 봐요","사용하지 않는 전자 제품의 플러그는 모조리 뽑아버립시다!"}));

        challengeLists.add(new ChallengeList("매립지에 점점 늘어나는 쓰레기를 줄여볼까요?","쓰레기를 줄이면 토양, 대기, 수질 오염을 감소시킬 수 있습니다.\n" +
                "지구의 생태계를 보존하는데 동참해주세요.\n",new String[]{"비닐봉지 대신 쇼핑백 들어봐요.","오래된 물건은 버리지 말고 기부하거나 판매해볼까요?"}));

        challengeLists.add(new ChallengeList("올바른 분리수거를 실천해볼래요?","올바른 분리수거의 핵심 4가지를 기억해요.\n" +
                "1) 비운다, 2) 헹군다, 3) 분리한다, 4) 섞지 않는다\n" +
                "지구의 생태계를 보존하는데 동참해주세요.\n",new String[]{"플라스틱 페트병은\n" +
                "뚜껑과 은박지, 라벨 등을 제거하여 배출해요.\n","비닐류는\n" +
                "이물질을 깨끗하게 비우고 물기를 제거해요. 오래된 비닐은 종량제 봉투에 쏙!\n"}));

        challengeLists.add(new ChallengeList("소중한 지구를 지켜주세요.","작은 실천으로 하나뿐인 소중한 지구를 지켜봐요. \n" +
                "여러분들의 실천들이 모여 지구는 건강해질 거예요.\n",new String[]{"이메일을 정리해봐요!","기름 묻은 기름은 휴지로 닦은 후 설거지해요."}));

    }
}
