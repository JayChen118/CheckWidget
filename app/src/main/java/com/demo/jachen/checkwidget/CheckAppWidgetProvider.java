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


        DateFormat format = new SimpleDateFormat("hh:mm:ss", Locale.CHINA);
        Log.d(TAG, "onUpdate: ");
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            int appWidgetId = appWidgetIds[i];

            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.check_widget);
            views.setOnClickPendingIntent(R.id.button, pendingIntent);


            views.setTextViewText(R.id.button, format.format(new Date(System.currentTimeMillis())));

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}
