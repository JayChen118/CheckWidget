package com.demo.jachen.checkwidget.bean;

import com.demo.jachen.checkwidget.utils.Checker;
import com.demo.jachen.checkwidget.utils.TimeUtil;

import java.util.Date;

/**
 * Created by JayChen on 2017/12/23.
 */

public class Diary {

    public long time;

    public String date;

    public String content;

    public Diary(long time, String content) {
        this.time = time;
        this.content = content;
        this.date = TimeUtil.DATE.format(new Date(time));
    }

    public long getTime() {
        return time;
    }

    public String getDate() {
        if (Checker.isEmpty(date)) {
            date = TimeUtil.DATE.format(new Date(time));
        }
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Diary{" +
                "time=" + time +
                ", date='" + date + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
