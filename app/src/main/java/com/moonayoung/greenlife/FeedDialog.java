package com.moonayoung.greenlife;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.moonayoung.greenlife.camera.CameraActivity;
import com.moonayoung.greenlife.challenge.FragmentChallenge;

public class FeedDialog {
    private Context context;
    String imageUrl;
    String nickName;

    public FeedDialog(Context context, String imageUrl, String nickName) {
        this.context = context;
        this.imageUrl = imageUrl;
        this.nickName = nickName;
    }

    // 호출할 다이얼로그 함수를 정의한다.
    public void callFunction() {
        Log.d("이미지다이얼", imageUrl);
        // 커스텀 다이얼로그를 정의하기위해 Dialog클래스를 생성한다.
        final Dialog dlg = new Dialog(context);

        // 커스텀 다이얼로그의 레이아웃을 설정한다.
        dlg.setContentView(R.layout.feed_dialog);
        dlg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // 커스텀 다이얼로그를 노출한다.
        dlg.show();

        // 커스텀 다이얼로그의 각 위젯들을 정의한다.
        TextView nicknameText = (TextView) dlg.findViewById(R.id.feed_nickName);
        ImageView feedImage = (ImageView) dlg.findViewById(R.id.feed_imageView);

        nicknameText.setText(nickName+"님의 챌린지");
        Glide.with(context).load(imageUrl).into(feedImage);

    }
}
