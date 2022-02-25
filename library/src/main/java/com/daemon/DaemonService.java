package com.daemon;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

public class DaemonService extends BaseService {

    private final static int NOTIFY_ID = 101;
    Notification notification;
    NotificationManager manager;
    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(){
            @Override
            public void run() {
                Daemon.getCallback().onStart();
            }
        }.start();

        Intent intent2 = new Intent();
        intent2.setClassName(getPackageName(), AssistService1.class.getName());
        startService(intent2);

        Intent intent3 = new Intent();
        intent3.setClassName(getPackageName(), AssistService2.class.getName());
        startService(intent3);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        showNotification();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        startForeground(NOTIFY_ID,notification);}
        return Service.START_NOT_STICKY;
    }

    private void showNotification() {
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent hangIntent = new Intent();//todo  test

        PendingIntent hangPendingIntent = PendingIntent.getActivity(this, 1002, hangIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        String CHANNEL_ID = "48098DDD-C1F8-4CA0-9BE9-A1466CF692B2";//应用频道Id唯一值， 长度若太长可能会被截断，
        String CHANNEL_NAME = "channel_id";//最长40个字符，太长会被截断
        notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Daemon Title")
                .setContentText("Daemon Content")
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentIntent(hangPendingIntent)
                .setAutoCancel(true)
                .build();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,
                    CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW);
            manager.createNotificationChannel(notificationChannel);
        }

        manager.notify(NOTIFY_ID, notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Daemon.getCallback().onStop();
    }
}
