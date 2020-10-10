package com.moonayoung.greenlife.intro;

import android.os.Bundle;
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

import com.moonayoung.greenlife.R;
import com.moonayoung.greenlife.api.JoinPost;
import com.moonayoung.greenlife.api.RetrofitClient;
import com.moonayoung.greenlife.api.User;
import com.moonayoung.greenlife.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinFragment extends Fragment {

    String email;
    String nickname;
    String passwd;

    //AccountManager am;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_join,container,false);


        email = ((EditText)rootView.findViewById(R.id.email_join)).getText().toString();
        nickname = ((EditText)rootView.findViewById(R.id.nickname_join)).getText().toString();
        passwd = ((EditText)rootView.findViewById(R.id.passwd_join)).getText().toString();

        Button joinBT2 = rootView.findViewById(R.id.joinBT2);
        joinBT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email!=null && passwd!=null){
                    ((IntroActivity)getActivity()).setFinish();
                    postJoin();
                }
            }
        });

        return rootView;
    }
    private void postJoin(){
        //am = AccountManager.get(getContext());
        User user = new User(email, nickname,passwd);
        System.out.println("여기까지 진행?");

        RetrofitClient.getApiService().postSignup("application/json; charset=utf-8",user).enqueue(new Callback<JoinPost>() {
            @Override
            public void onResponse(Call<JoinPost> call, Response<JoinPost> response) {
                System.out.println(response.toString());
                if(response.isSuccessful()){
                    JoinPost joinPost = response.body();
                    System.out.println(joinPost);
                    System.out.println(joinPost.getCheck());
                    String check2 = null;
                    boolean check = joinPost.getCheck();
                    if(check == true)  check2 = "true";
                    else check2 = "false";
                    System.out.println("여기까지 진행?(2)");

                    if(joinPost != null){
                        //final Account account = new Account(email, SyncStateContract.Constants.ACCOUNT_TYPE);
                        //am.setAuthToken(account, SyncStateContract.Constants.ACCOUNT_TYPE, token);

                        //임시
                        //Toast.makeText(getContext(),check2,Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    System.out.println(response.message());
                    System.out.println(response.errorBody());
                    Toast.makeText(getContext(),email,Toast.LENGTH_LONG).show();
                    Log.d("fjkad","ㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗㅗ안됨ㅏㄴ도");
/*                    MainActivity mainActivity = (MainActivity)getActivity();
                    String token = mainActivity.getToken();*/

                }
            }



            @Override
            public void onFailure(Call<JoinPost> call, Throwable t) {
                System.out.println(t.getMessage());
                Log.d("fjkad","=========================================failedfailed");
            }
        });
    }

}
