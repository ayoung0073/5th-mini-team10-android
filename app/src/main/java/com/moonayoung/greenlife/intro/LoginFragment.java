package com.moonayoung.greenlife.intro;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.moonayoung.greenlife.api.ApiService;
import com.moonayoung.greenlife.R;
import com.moonayoung.greenlife.api.JoinPost;
import com.moonayoung.greenlife.api.LoginPost;
import com.moonayoung.greenlife.api.RetrofitClient;
import com.moonayoung.greenlife.api.User;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginFragment extends Fragment {
    private static String token;
    private static String nickname;
    EditText email;
    EditText passwd;
    TextView warning;
//    private String token; //세부 챌린지에서 사용자 토큰 접근하기 위한 변수

//    AccountManager am = AccountManager.get(getContext());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_login,container,false);

        email = rootView.findViewById(R.id.email_login);
        passwd = rootView.findViewById(R.id.passwd_login);
        warning = rootView.findViewById(R.id.warning_login);

        Button loginBT2 = rootView.findViewById(R.id.loginBT2);
        loginBT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email!=null && passwd!=null){
                    postLogin();
                }
                else{
                    email.setText("");
                    passwd.setText("");
                    if(!email.getText().toString().contains("@"))
                        warning.setText("잘못된 이메일 형식입니다.");
                    else warning.setText("다시 입력해주세요.");
                }
            }
        });
        return rootView;
    }

    private void postLogin(){
        User user = new User(email.getText().toString(), passwd.getText().toString());

        RetrofitClient.getApiService()
                .postLogin("application/json; charset=utf-8", user).enqueue(new Callback<LoginPost>() {
            @Override
            public void onResponse(Call<LoginPost> call, Response<LoginPost> response) {
                System.out.println(response.toString());
                System.out.println(response.errorBody());
                if(response.isSuccessful()){
                    LoginPost loginPost = response.body();
                    System.out.println(loginPost.getNickname());
                    System.out.println(loginPost.getMessage());

                    System.out.println(email.getText().toString());
                    System.out.println(passwd.getText().toString());
                    Log.d("로그인프래그먼트",""+loginPost.getToken());


                    if(!loginPost.isSuccess()){
                        warning.setText("다시 로그인해주세요.");
                        email.setText("");
                        passwd.setText("");
                    }
                    else{
                        warning.setText("");
                        Toast.makeText(getContext(),loginPost.getNickname()+"님 환영합니다!",Toast.LENGTH_LONG).show();
                        ((IntroActivity)getActivity()).setFinish();

                    }
                    Log.d("로그인 성공", "   "+loginPost.isSuccess());
                    Log.d("로그인 닉넴", "   "+loginPost.getNickname());
                    Log.d("로그인 토큰", "     "+loginPost.getToken());

                    token = loginPost.getToken();
                    nickname = loginPost.getNickname();
                }
                else{
                    Toast.makeText(getContext(),"응답안옴",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<LoginPost> call, Throwable t) {
            }
        });
    }
    public static String getToken(){
        return token;
    }
    public static String getNickname(){
        return nickname;
    }
    }



