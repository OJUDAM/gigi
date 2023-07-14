package com.ujo.test.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {

    public static String addDate(String pattern, int days) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DATE, days);

        return format.format(calendar.getTime());
    }
}
