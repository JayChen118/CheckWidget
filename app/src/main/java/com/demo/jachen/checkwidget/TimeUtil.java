package com.demo.jachen.checkwidget;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jachen on 10/31/2017.
 */

public class TimeUtil {

    public static final DateFormat DAY_TIME = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
    public static final DateFormat YEAR_MONTH = new SimpleDateFormat("yyyy-MM", Locale.CHINA);

    public static String getTime() {
        return DAY_TIME.format(new Date());
    }

    public static String getYearMonth() {
        return YEAR_MONTH.format(new Date());
    }

    public static String getDayOfMonth() {
        return String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
    }
}
