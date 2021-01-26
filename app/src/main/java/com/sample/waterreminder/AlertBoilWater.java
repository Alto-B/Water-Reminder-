package com.sample.waterreminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static com.sample.waterreminder.NotificationHelper.CHANNEL_2_ID;

public class AlertBoilWater extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        NotificationCompat.Builder notification = new NotificationCompat.Builder(context,CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_opacity_black_24dp)
                .setContentTitle("Boil Water!")
                .setContentText("Time to boil some water")
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        notificationManager.notify(2,notification.build());
    }
}
