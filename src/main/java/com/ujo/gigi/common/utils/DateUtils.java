package com.ujo.gigi.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static String addDate(String pattern, int days) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DATE, days);

        return format.format(calendar.getTime());
    }

    public static long parseTime(String time) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            Date dateTime = dateFormat.parse(dateToTime(time));

            return dateTime.getTime();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static long timeDiff(String targetTime, String sourceTime) {
        long time1 = DateUtils.parseTime(targetTime);
        long time2 = DateUtils.parseTime(sourceTime);

        return (time1 - time2) / 1000;
    }

    public static String dateToTime(String dateTime) {
        return dateTime.substring(11, 19);
    }
    public static LocalDateTime parse(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateTime, formatter);

    }
}
