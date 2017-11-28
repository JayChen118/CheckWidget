package com.demo.jachen.checkwidget.bean;

/**
 * Created by jachen on 11/28/2017.
 */

public class Book implements Target {

    private String name;

    private String targetTime = "21:00";

    private int alertMinute = 120;

    private int intervalMinute = 15;

    public Book(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTargetTime() {
        return targetTime;
    }

    public void setTargetTime(String targetTime) {
        this.targetTime = targetTime;
    }

    public int getAlertMinute() {
        return alertMinute;
    }

    public void setAlertMinute(int alertMinute) {
        this.alertMinute = alertMinute;
    }

    public int getIntervalMinute() {
        return intervalMinute;
    }

    public void setIntervalMinute(int intervalMinute) {
        this.intervalMinute = intervalMinute;
    }


    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", targetTime='" + targetTime + '\'' +
                ", alertMinute=" + alertMinute +
                ", intervalMinute=" + intervalMinute +
                '}';
    }
}
