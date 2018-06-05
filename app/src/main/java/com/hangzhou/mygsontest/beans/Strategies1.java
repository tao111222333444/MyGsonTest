package com.hangzhou.mygsontest.beans;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author 29794
 */
public class Strategies1 {
    private Date date;

    private Date date2;

    public Strategies1(Date date, Date date2) {
        this.date = date;
        this.date2 = date2;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS", Locale.CHINA);
        return "Strategies{" +
                "date=" + simpleDateFormat.format(date) +
                ", date2=" + simpleDateFormat.format(date2) +
                '}';
    }
}
