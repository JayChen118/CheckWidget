package com.demo.jachen.checkwidget.bean;

import com.demo.jachen.checkwidget.utils.TimeUtil;

import java.util.Date;

/**
 * Created by JayChen on 2017/12/23.
 */

public class Diary {

    public long time;

    public String content;

    public Diary(long time, String content) {
        this.time = time;
        this.content = content;
    }

    public long getTime() {
        return time;
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
                "time=" + TimeUtil.DATE_TIME.format(new Date(time)) +
                ", content='" + content + '\'' +
                '}';
    }
}
