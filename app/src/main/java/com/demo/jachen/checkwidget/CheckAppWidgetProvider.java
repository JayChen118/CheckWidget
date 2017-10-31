package com.demo.jachen.checkwidget;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by jachen on 10/18/2017.
 */

public class CheckAppWidgetProvider extends AppWidgetProvider {
    private static final String TAG = "CheckAppWidgetProvider";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);


        Log.d(TAG, "onUpdate: ");
        for (int appWidgetId : appWidgetIds) {
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.check_widget);
            views.setOnClickPendingIntent(R.id.button, pendingIntent);

            views.setTextViewText(R.id.button, String.format("Update:%s", TimeUtil.getTime()));

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }

        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent serviceIntent = new Intent(context, MyIntentService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, serviceIntent, 0);
        if (manager != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, 1);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 60000, pendingIntent);
        }

//        context.startService(new Intent(context, MyIntentService.class));

        NotificationLauncher.fire(context);
    }

    private List<Event> mockEvents() {
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Event event = new Event();
            event.setName("Event" + i);
            event.setStart(System.currentTimeMillis());
            event.setInterval(1000 * 60 * 10);
            events.add(event);
        }
        return events;
    }


    private void setAlarm(Context context) {

        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        long triggerAtTime = System.currentTimeMillis() + 60000;
        Intent intent = new Intent(context, MyIntentService.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (manager != null) {
            manager.set(AlarmManager.RTC_WAKEUP, triggerAtTime, pi);
        }
    }
}
