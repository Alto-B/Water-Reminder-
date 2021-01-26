package com.sample.waterreminder;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class NotificationHelper extends Application {
    public static final String CHANNEL_1_ID = "channel1";
    public static final String CHANNEL_2_ID = "channel2";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel1 = new NotificationChannel(CHANNEL_1_ID,"Water Reminder", NotificationManager.IMPORTANCE_HIGH);
            NotificationChannel channel2 = new NotificationChannel(CHANNEL_2_ID,"Boil Reminder", NotificationManager.IMPORTANCE_HIGH);

            channel1.setDescription("Notify User to drink water reminder");
            channel2.setDescription("Notify User to boil water");

            NotificationManager manager = getSystemService(NotificationManager.class);

            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
        }
    }
}
