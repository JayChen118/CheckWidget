package com.demo.jachen.checkwidget.utils;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jachen on 10/31/2017.
 */

public class TimeUtil {

    public static final DateFormat DATE_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
    public static final DateFormat DAY_TIME = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
    public static final DateFormat YEAR_MONTH = new SimpleDateFormat("yyyy-MM", Locale.CHINA);
    public static final DateFormat HOUR_MINUTE = new SimpleDateFormat("HH:mm", Locale.CHINA);

    public static String getTime() {
        return DAY_TIME.format(new Date());
    }

    public static String getYearMonth() {
        return YEAR_MONTH.format(new Date());
    }

    public static int getDayOfMonth() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    public static Date concat(String hourMinute) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        try {
            Date date = HOUR_MINUTE.parse(hourMinute);
            Calendar temp = Calendar.getInstance();
            temp.setTime(date);
            Log.d("MyIntentService", "concat: " + date);
            calendar.set(Calendar.HOUR, temp.get(Calendar.HOUR));
            calendar.set(Calendar.MINUTE, temp.get(Calendar.MINUTE));
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Log.d("MyIntentService", "concat: " + calendar.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar.getTime();
    }

    public static boolean isInRange(Date date, int alertMinute) {
        Log.d("MyIntentService", "isInRange: date: " + date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, -alertMinute);
        final long left = calendar.getTimeInMillis();
        Log.d("MyIntentService", "isInRange: left: " + new Date(left));
        calendar.add(Calendar.MINUTE, 2 * alertMinute);
        final long right = calendar.getTimeInMillis();
        Log.d("MyIntentService", "isInRange: right: " + new Date(right));

        return System.currentTimeMillis() >= left && System.currentTimeMillis() <= right;
    }

}
