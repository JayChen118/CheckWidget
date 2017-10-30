package com.demo.jachen.checkwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jachen on 10/18/2017.
 */

public class CheckAppWidgetProvider extends AppWidgetProvider {
    private static final String TAG = "CheckAppWidgetProvider";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);


        DateFormat format = new SimpleDateFormat("hh:mm", Locale.CHINA);
        Log.d(TAG, "onUpdate: ");
        for (int appWidgetId : appWidgetIds) {
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.check_widget);
            views.setOnClickPendingIntent(R.id.button, pendingIntent);

            views.setTextViewText(R.id.button, format.format(new Date(System.currentTimeMillis())));

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }

        NotificationLauncher.fire(context);
    }

    private void setAlarm(Context context) {

/*        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        long triggerAtTime = System.currentTimeMillis() + 60000;
        Intent intent = new Intent(context, NotificationReceiver.class);
        intent.setAction(NOTIFICATION_ACTION_PARENT);
        intent.putExtra(NOTIFICATION_CONTENT_KEY, notificationContent);
        intent.putExtra(NOTIFICATION_ACTION, NOTIFICATION_ACTION_PARENT);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        manager.set(AlarmManager.RTC_WAKEUP, triggerAtTime, pi);*/
    }
}
