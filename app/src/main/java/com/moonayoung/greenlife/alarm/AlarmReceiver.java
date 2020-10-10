package com.moonayoung.greenlife.alarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.moonayoung.greenlife.R;
import com.moonayoung.greenlife.MainActivity;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // NotificationManager 안드로이드 상태바에 메시지 띄우기 위한 서비스 불러옴

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class),PendingIntent.FLAG_UPDATE_CURRENT);
        // 푸시 알림 설정
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setWhen(System.currentTimeMillis()).setSmallIcon(R.drawable.temp).setContentTitle("그린라이프").setContentText("오늘도 실천해볼까요").setContentIntent(pendingIntent).setAutoCancel(true);
        Notification notification = builder.build();

        manager.notify(1,notification);
    }
}
