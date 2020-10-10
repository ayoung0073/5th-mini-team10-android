package com.moonayoung.greenlife.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//서버 url을 설정하고 데이터 파싱 및 객체 정보를 반환할 수 있는 Retrofit 객체를 하나 생성하고
//ApiService를 활용할 수 있는 클래스도 하나 생성해 줍니다.

public class RetrofitClient {

    //객체생성
    Retrofit retrofit = new Retrofit.Builder()
            //서버 url설정
            .baseUrl(TaskServer.ip)
            //데이터 파싱 설정
            .addConverterFactory(GsonConverterFactory.create())
            //객체정보 반환
            .build();

    public ApiService apiService = retrofit.create(ApiService.class);
}
