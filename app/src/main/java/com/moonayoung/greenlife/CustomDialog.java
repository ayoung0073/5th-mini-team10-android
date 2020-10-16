package com.moonayoung.greenlife;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.moonayoung.greenlife.camera.CameraActivity;
import com.moonayoung.greenlife.challenge.FragmentChallenge;

public class CustomDialog {
    private Context context;
    String nickName;

    public CustomDialog(Context context, String nickNmae) {
        this.context = context;
        this.nickName = nickNmae;
    }

    // 호출할 다이얼로그 함수를 정의한다.
    public void callFunction() {

        // 커스텀 다이얼로그를 정의하기위해 Dialog클래스를 생성한다.
        final Dialog dlg = new Dialog(context);

        // 커스텀 다이얼로그의 레이아웃을 설정한다.
        dlg.setContentView(R.layout.custom_dialog);
        dlg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        출처: https://jhshjs.tistory.com/60 [독학하는 1인 개발자]
        // 커스텀 다이얼로그를 노출한다.
        dlg.show();

        // 커스텀 다이얼로그의 각 위젯들을 정의한다.
        TextView nicknameText = (TextView) dlg.findViewById(R.id.nickname);
        CardView takeAPicture = (CardView) dlg.findViewById(R.id.takeAPicture);
        TextView noPicture = (TextView) dlg.findViewById(R.id.noPicture);

        nicknameText.setText(nickName);

        takeAPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CameraActivity.class);
                context.startActivity(intent);
                dlg.dismiss();
            }
        });
        noPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 커스텀 다이얼로그를 종료한다.
                dlg.dismiss();
            }
        });
    }
}
