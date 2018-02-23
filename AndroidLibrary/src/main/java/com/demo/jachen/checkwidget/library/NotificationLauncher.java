package com.demo.jachen.checkwidget.library;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.demo.jachen.checkwidget.library.utils.TimeUtil;

/**
 * Created by jachen on 10/30/2017.
 */

public class NotificationLauncher {

    public static void fire(Context context, String target) {
/*
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

// The id of the channel.
        String id = "my_channel_01";

// The user-visible name of the channel.
        CharSequence name = context.getString(R.string.channel_name);

// The user-visible description of the channel.
        String description = context.getString(R.string.channel_description);

        int importance = NotificationManager.IMPORTANCE_LOW;



        NotificationChannel mChannel = new NotificationChannel(id, name,importance);

// Configure the notification channel.
        mChannel.setDescription(description);

        mChannel.enableLights(true);
// Sets the notification light color for notifications posted to this
// channel, if the device supports this feature.
        mChannel.setLightColor(Color.RED);

        mChannel.enableVibration(true);
        mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});

        mNotificationManager.createNotificationChannel(mChannel);*/

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "jay")
                .setSmallIcon(com.demo.jachen.checkwidget.library.R.mipmap.ic_launcher)
                .setContentTitle("Check! " + target)
                .setContentText(String.format("It's %s.", TimeUtil.getTime()))
                .setAutoCancel(true);
        Intent resultIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, resultIntent, 0);

        builder.setContentIntent(pendingIntent);
        builder.setDefaults(Notification.DEFAULT_SOUND);
//        builder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null) {
            manager.notify(1, builder.build());
        }
    }
}
