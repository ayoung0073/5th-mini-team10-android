package com.moonayoung.greenlife.intro;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.moonayoung.greenlife.R;
import com.moonayoung.greenlife.api.ApiService;
import com.moonayoung.greenlife.api.User;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class JoinFragment extends Fragment {
    ApiService apiService;

    String email;
    String nickname;
    String passwd;

//    AccountManager am = AccountManager.get(getContext());

    Retrofit retrofit;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_join,container,false);

        apiService = retrofit.create(ApiService.class);

        email = ((EditText)rootView.findViewById(R.id.email_join)).getText().toString();
        nickname = ((EditText)rootView.findViewById(R.id.nickname_join)).getText().toString();
        passwd = ((EditText)rootView.findViewById(R.id.passwd_join)).getText().toString();

        Button joinBT2 = rootView.findViewById(R.id.joinBT2);
        joinBT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((IntroActivity)getActivity()).setFinish();
                if(email!=null && passwd!=null){
                    ((IntroActivity)getActivity()).setFinish();

                    //postJoin();
                }
            }
        });

        return rootView;
    }
/*    private void postJoin(){
        User user = new User(email, passwd);
        HashMap<String, String> map = new HashMap<>();
        map.put("email",user.getEmail());
        map.put("nickname",user.getNickname());
        map.put("passwd",user.getPasswd());
        Call<String> call = apiService.postLogin(map);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    final String token = response.body();

                    if(token != null){
                        final Account account = new Account(email, SyncStateContract.Constants.ACCOUNT_TYPE);
                        am.setAuthToken(account, SyncStateContract.Constants.ACCOUNT_TYPE, token);

                        //임시
                        Toast.makeText(getContext(),"환영합니다",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }*/

}
