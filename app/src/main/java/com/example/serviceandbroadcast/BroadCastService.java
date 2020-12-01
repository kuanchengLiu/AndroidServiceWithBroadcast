package com.example.serviceandbroadcast;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class BroadCastService extends Service {

    private int sum = 0;
    private boolean running = true;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();
        String CHANNEL_ONE_ID = "";
        String CHANNEL_ONE_NAME = "Channel One";
        NotificationChannel notificationChannel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel(CHANNEL_ONE_ID,
                    CHANNEL_ONE_NAME, NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setShowBadge(true);
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(notificationChannel);
        }
        Log.d("ServiceProject", "onCreate");
//        thread.start();
        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplication(), 0, activityIntent, 0);
        Notification notification = new Notification.Builder(getApplication()).setChannelId(CHANNEL_ONE_ID).setContentIntent(pendingIntent).build();
        startForeground(1, notification);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("ServiceProject", "onDestroy");
        running = false;
    }
    long[] vibrate = {0,100,200,300};

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);

    }
    private Handler samHandler=new Handler();

    Thread thread = new Thread(new Runnable() {

        @Override
        public void run() {
            while (running) {
                sum++;
                Log.d("ServiceProject", "ServiceSwe : " + sum);
                Intent intent = new Intent();
                intent.putExtra("Swe_sum", "" + sum);
                intent.setAction("android.intent.action.test");
                sendBroadcast(intent);
//                samHandler.postDelayed(this, 1000);
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    running = false;
//                    e.printStackTrace();
//                }
            }
        }
    });
}
