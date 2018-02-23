package com.demo.jachen.checkwidget.library;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.demo.jachen.checkwidget.library.activity.DiaryActivity;
import com.demo.jachen.checkwidget.library.bean.Book;
import com.demo.jachen.checkwidget.library.repository.DiaryRepository;
import com.demo.jachen.checkwidget.library.utils.SharedPreferencesUtil;
import com.demo.jachen.checkwidget.library.utils.TimeUtil;

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

        RemoteViews views = new RemoteViews(getPackageName(), com.demo.jachen.checkwidget.library.R.layout.check_widget);

        String temp = SharedPreferencesUtil.readRecord();
        temp = String.format("Alarm:%s", TimeUtil.getTime()) + "\n" + temp;
        if (temp.length() > 1000) {
            temp = temp.substring(0, 1000);
        }
        SharedPreferencesUtil.storeRecord(temp);

        views.setTextViewText(com.demo.jachen.checkwidget.library.R.id.button, temp);

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

        useForeground("abc", "ffff");
    }

    private boolean shouldAlert(Book book) {
        Log.d(TAG, "shouldAlert: isFinished: " + SharedPreferencesUtil.isFinishedToday(book.getName()));
        if (!SharedPreferencesUtil.isFinishedToday(book.getName())) {
            Log.d(TAG, "shouldAlert: isInRange1: " + TimeUtil.isInRange(TimeUtil.concat(book.getTargetTime()), book.getAlertMinute()));
            if (TimeUtil.isInRange(TimeUtil.concat(book.getTargetTime()), book.getAlertMinute())) {
                Log.d(TAG, "shouldAlert: isNotInRange: " + !TimeUtil.isInRange(new Date(
                        SharedPreferencesUtil.getLastAlertTime(book.getName())), book.getIntervalMinute()));
                Log.d(TAG, "shouldAlert: lastAlert: " + new Date(SharedPreferencesUtil.getLastAlertTime(book.getName())));
                if (!TimeUtil.isInRange(new Date(SharedPreferencesUtil.getLastAlertTime(book.getName())), book.getIntervalMinute())) {
                    return true;
                }
            }
        }
        return false;
    }


    private void startDiaryAlert(Context context) {
        if (!DiaryRepository.isTodayDairyFinished()) {
            Notification notification = new Notification(R.drawable.ic_launcher_background, "diary",
                    System.currentTimeMillis());
            Intent notificationIntent = new Intent(context, DiaryActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
//
//            notification.setLatestEventInfo(this, "DiaryText",
//                    getText(R.string.notification_message), pendingIntent);

            startForeground(11, notification);
        }
    }

    public void useForeground(CharSequence tickerText, String currSong) {
        Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, 0);
    /* Method 01
     * this method must SET SMALLICON!
     * otherwise it can't do what we want in Android 4.4 KitKat,
     * it can only show the application info page which contains the 'Force Close' button.*/
        NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(MyIntentService.this)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setTicker(tickerText)
                .setWhen(System.currentTimeMillis())
                .setContentTitle(getString(R.string.app_name))
                .setContentText(currSong)
                .setContentIntent(pendingIntent);
        Notification notification = mNotifyBuilder.build();

    /* Method 02
    Notification notification = new Notification(R.drawable.ic_launcher, tickerText,
            System.currentTimeMillis());
    notification.setLatestEventInfo(PlayService.this, getText(R.string.app_name),
            currSong, pendingIntent);
    */

        startForeground(12, notification);
    }

}
