package com.demo.jachen.checkwidget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.demo.jachen.checkwidget.bean.Book;
import com.demo.jachen.checkwidget.utils.SharedPreferencesUtil;
import com.demo.jachen.checkwidget.utils.TimeUtil;

import java.util.Date;
import java.util.List;

public class MyIntentService extends IntentService {
    private static final String TAG = "MyIntentService";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent: ");

        ComponentName componentName = new ComponentName(this, CheckAppWidgetProvider.class);

        RemoteViews views = new RemoteViews(getPackageName(), R.layout.check_widget);

        String temp = SharedPreferencesUtil.readRecord();
        temp = String.format("Alarm:%s", TimeUtil.getTime()) + "\n" + temp;
        if (temp.length() > 1000) {

            temp = temp.substring(0, 1000);
        }
        SharedPreferencesUtil.storeRecord(temp);

        views.setTextViewText(R.id.button, temp);

        List<Book> books = SharedPreferencesUtil.getBooks();
        Log.d(TAG, "onHandleIntent: books: " + books);
        for (Book book : books) {
            if (shouldAlert(book)) {
                NotificationLauncher.fire(this, book.getName());
                SharedPreferencesUtil.logLastAlertTime(book.getName(), System.currentTimeMillis());
                Log.d(TAG, "onHandleIntent: fire: " + TimeUtil.getTime());
            }
        }


        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        appWidgetManager.updateAppWidget(componentName, views);
    }

    private boolean shouldAlert(Book book) {
        Log.d(TAG, "shouldAlert: isFinished: " + SharedPreferencesUtil.isFinishedToday(book.getName()));
        if (!SharedPreferencesUtil.isFinishedToday(book.getName())) {
            Log.d(TAG, "shouldAlert: isInRange1: " + TimeUtil.isInRange(TimeUtil.concat(book.getTargetTime()), book.getAlertMinute()));
            if (TimeUtil.isInRange(TimeUtil.concat(book.getTargetTime()), book.getAlertMinute())) {
                Log.d(TAG, "shouldAlert: isNotInRange: " + !TimeUtil.isInRange(new Date(SharedPreferencesUtil.getLastAlertTime(book.getName())), book.getIntervalMinute()));
                Log.d(TAG, "shouldAlert: lastAlert: " + new Date(SharedPreferencesUtil.getLastAlertTime(book.getName())));
                if (!TimeUtil.isInRange(new Date(SharedPreferencesUtil.getLastAlertTime(book.getName())), book.getIntervalMinute())) {
                    return true;
                }
            }
        }
        return false;
    }

}
