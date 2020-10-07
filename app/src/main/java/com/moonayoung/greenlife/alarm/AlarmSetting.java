package com.moonayoung.greenlife.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

public class AlarmSetting {
    private Context context; // 뭐지
    public AlarmSetting(Context context){
        this.context = context;
    }

    public void Alarm(){
        AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        // 알람매니저 : 디바이스에서 나중에 알림 행위 등록할 때 사용
        Intent intent = new Intent(context, AlarmReceiver.class);
        // 알림 발생 시, Alarm 리시버에 방송해주기 위해 명시적으로 알림

        PendingIntent sender = PendingIntent.getBroadcast(context,0,intent, 0);
        // notification으로 ( + alarmManager ) intent 수행 시 pendingintent

        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DATE),10,0,0);

        // 알람 예약
        am.set(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),sender);
    }
}
