package com.demo.jachen.checkwidget;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jachen on 10/31/2017.
 */

public class TimeUtil {

    public static final DateFormat DAY_TIME = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);

    public static String getTime() {
        return DAY_TIME.format(new Date());
    }
}
