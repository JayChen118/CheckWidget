package com.demo.jachen.checkwidget;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by jachen on 10/30/2017.
 */

public class NotificationLauncher {

    public static void fire(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "jay")
                .setSmallIcon(R.mipmap.ic_launcher).setContentTitle("Check!")
                .setContentText(String.format("It's %s.", TimeUtil.getTime())).setAutoCancel(true);
        Intent resultIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, resultIntent, 0);

        builder.setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null) {
            manager.notify(1, builder.build());
        }
    }
}
