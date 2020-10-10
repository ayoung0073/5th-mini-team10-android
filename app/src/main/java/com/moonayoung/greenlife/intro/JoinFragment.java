package com.moonayoung.greenlife.intro;

import android.os.Bundle;
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

import com.moonayoung.greenlife.R;
import com.moonayoung.greenlife.api.JoinPost;
import com.moonayoung.greenlife.api.RetrofitClient;
import com.moonayoung.greenlife.api.User;
import com.moonayoung.greenlife.MainActivity;

import okhttp3.Interceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinFragment extends Fragment {

    EditText email;
    EditText nickname;
    EditText passwd;
    TextView warning;

    //AccountManager am;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_join,container,false);


        email = rootView.findViewById(R.id.email_join);
        nickname = rootView.findViewById(R.id.nickname_join);
        passwd = rootView.findViewById(R.id.passwd_join);
        warning = rootView.findViewById(R.id.warning_join);

        Button joinBT2 = rootView.findViewById(R.id.joinBT2);
        joinBT2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((email.getText().toString()!=null && (email.getText().toString()).contains("@")) && passwd.getText().toString()!= null){
                    //((IntroActivity)getActivity()).setFinish();
                    postJoin();
                }
                else{
                    email.setText("");
                    passwd.setText("");
                    warning.setText("잘못된 이메일 형식입니다.");
                }
            }
        });

        return rootView;
    }
    private void postJoin(){
        //am = AccountManager.get(getContext());
        User user = new User(email.getText().toString(), nickname.getText().toString(),passwd.getText().toString());
        System.out.println("여기까지 진행?");

        RetrofitClient.getApiService().postSignup("application/json; charset=utf-8",user).enqueue(new Callback<JoinPost>() {
            @Override
            public void onResponse(Call<JoinPost> call, Response<JoinPost> response) {
                System.out.println(response.toString());
                if(response.isSuccessful()){
                    JoinPost joinPost = response.body();
                    //System.out.println(joinPost); 주소출력됨
                    System.out.println(joinPost.isSuccess());
                    System.out.println(joinPost.getMessage());

                    System.out.println(email);
                    System.out.println(passwd);

                    boolean check = joinPost.isSuccess();
                    if(check == true)  {
                        warning.setText("");
                        Toast.makeText(getContext(), "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        ((IntroActivity)getActivity()).setFragment("login2");
                        //getFragmentManager().beginTransaction().remove((JoinFragment)getParentFragment()).commit();
                        //getFragmentManager().beginTransaction().replace(R.id.frameContainer,new JoinFragment()).commit();
                    }
                    else {
                        warning.setText("이미 등록된 이메일입니다.");
                        email.setText("");
                        passwd.setText("");
                    }

                }
                else{
                    System.out.println(response.message());
                    System.out.println(response.errorBody());
                    Toast.makeText(getContext(),email.getText().toString(),Toast.LENGTH_LONG).show();
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
