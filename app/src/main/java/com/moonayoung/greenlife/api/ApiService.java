package com.moonayoung.greenlife.api;

import android.graphics.Bitmap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiService {

    @POST("user/login") // 로그인
    Call<LoginPost> postLogin(@Header("Content-Type") String content, @Body User param);

/*    @Headers({"Content-Type : application/json; charset=utf-8"})
    @FormUrlEncoded @POST("user/signup") // 회원가입
    Call<JoinPost> postSignup(@FieldMap HashMap<String,String> param);*/


    @POST("user/signup") // 회원가입
    Call<JoinPost> postSignup(@Header("Content-Type") String content, @Body User param);


    // 여러개 객체 응답?
    @GET("/challenge/all") // 챌린지목록(추천챌린지포함)
    Call<Challenge> getChallenges();

    @GET("/challenge/detail/{id}") // 챌린지상세
    Call<SubChallenge> getDetatilChallenges(@Header("token") String token, @Path("id") String challengeId);

    @PUT("/challenge/do/subchallenge/{id}") // 요청 바디로(참여버튼) -> 해당 세부챌린지 Id 보냄
    Call<Participate> putData(@Header("token") String token, @Path("id") String subChallengeId);

    @Multipart
    @POST("feed/upload") // 사진업로드 // 아직 사진객체 ?
    Call<UploadPost> postPhoto(@Header("token") String token, @Part MultipartBody.Part file);

    @GET("/feed") // feed 확인
    Call<Feed> feedUpload(@Header("Content-Type") String content);

    @GET("user/rank") // 명예의 전당
    Call<Rank> getRank(@Header("Content-Type") String content);


}
