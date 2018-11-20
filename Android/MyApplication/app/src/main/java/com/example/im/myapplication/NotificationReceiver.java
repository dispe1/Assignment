package com.example.im.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.Calendar;

public class NotificationReceiver extends BroadcastReceiver {
    String INTENT_ACTION = Intent.ACTION_BOOT_COMPLETED;
    public NotificationManager nm;
    String selectedGridDate;
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences memoPrefs = context.getSharedPreferences("memo", 0);
        SharedPreferences sf = context.getSharedPreferences("alarmSetting", 0);
        boolean saveAlarmSetting = sf.getBoolean("setting", false);

        if (saveAlarmSetting == true) {
            Calendar calendar = Calendar.getInstance();
            if ((calendar.get(Calendar.MONTH) + 1) < 10) {
                selectedGridDate = Integer.toString(calendar.get(Calendar.YEAR)) + "-0" + Integer.toString(calendar.get(Calendar.MONTH) + 1) + "-" + Integer.toString(calendar.get(Calendar.DATE));
            } else {
                selectedGridDate = Integer.toString(calendar.get(Calendar.YEAR)) + "-" + Integer.toString(calendar.get(Calendar.MONTH) + 1) + "-" + Integer.toString(calendar.get(Calendar.DATE));
            }
            String SavedMemo = memoPrefs.getString(selectedGridDate, "");

            String channelId = "channel";
            String channelName = "Channel Name";
            Notification.Builder builder;

            nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Intent intent1 = new Intent(context, MainActivity.class);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel mChannel = new NotificationChannel(
                        channelId, channelName, importance);
                nm.createNotificationChannel(mChannel);
                builder = new Notification.Builder(context, channelId);

            } else {
                builder = new Notification.Builder(context);
            }
            intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent PIntent = PendingIntent.getActivity(context, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
            String ticker = "오늘 일정";
            String title = selectedGridDate;
            String text = SavedMemo;
            builder.setSmallIcon(R.drawable.cau).setTicker(ticker).setWhen(System.currentTimeMillis())
                    .setNumber(1).setContentTitle(title).setContentText(text)
                    .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE).setContentIntent(PIntent).setAutoCancel(true).setOngoing(true);

            if(!text.equals("")) {
                nm.notify(1, builder.build());
            }
        }
    }
}
