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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.moonayoung.greenlife.api.ApiService;
import com.moonayoung.greenlife.R;
import com.moonayoung.greenlife.api.User;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginFragment extends Fragment {
    EditText email;
    EditText passwd;
    ApiService apiService;
    Retrofit retrofit;

    AccountManager am = AccountManager.get(getContext());
    Bundle options = new Bundle();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_login,container,false);
/*        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/
        apiService = retrofit.create(ApiService.class);

        email = rootView.findViewById(R.id.email_login);
        passwd = rootView.findViewById(R.id.passwd_login);

        Button loginBT2 = rootView.findViewById(R.id.loginBT2);
        loginBT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString()!=null && passwd.getText().toString()!=null){
                    postLogin();
                }
                ((IntroActivity)getActivity()).setFinish();
            }
        });
        return rootView;
    }

    private void postLogin(){
        User user = new User(email.getText().toString(), passwd.getText().toString());
        HashMap<String, String> map = new HashMap<>();
        map.put("email",user.getEmail());
        map.put("passwd",user.getPassword());
        Call<String> call = apiService.postLogin(map);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    final String token = response.body();

                    if(token != null){
                        final Account account = new Account(email.getText().toString(), SyncStateContract.Constants.ACCOUNT_TYPE);
                        am.setAuthToken(account, SyncStateContract.Constants.ACCOUNT_TYPE, token);

                        //임시
                        Toast.makeText(getContext(),"환영합니다",Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(getContext(),"응답안옴",Toast.LENGTH_LONG).show();
                    Log.d("fjkad","안됨ㅏㄴ도");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("fjkad","failed");
            }
        });
    }


    }

