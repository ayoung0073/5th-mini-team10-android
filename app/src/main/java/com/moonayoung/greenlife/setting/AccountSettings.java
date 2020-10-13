package com.moonayoung.greenlife.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.moonayoung.greenlife.MainActivity;
import com.moonayoung.greenlife.R;

public class AccountSettings extends AppCompatActivity {

    private Button btn_back;
    Button btn_nickname;
    Button btn_password;

    FragmentSetting fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);

        fragment = new FragmentSetting();

        //fragment = (FragmentSetting) getSupportFragmentManager().findFragmentById(R.id.frameLayout);

        btn_back = findViewById(R.id.backBT);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
                /*Intent intent = new Intent(AccountSettings.this, FragmentSetting.class);
                startActivity(intent);*/

            }
        });

        btn_nickname = findViewById(R.id.btn_changeNickname);
        btn_password = findViewById(R.id.btn_changePassword);

        btn_nickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    public void onFragmentChange(int index) {
        if (index == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
        }
    }
}