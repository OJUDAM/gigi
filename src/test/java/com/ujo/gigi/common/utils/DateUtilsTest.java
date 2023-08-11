package com.ujo.gigi.common.utils;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilsTest {

    @Test
    void parseTimeTest() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        Date dateTime = dateFormat.parse("13:13:14");

        String time1 = "1994-10-20 13:14:14";
        String time2 = "1994-10-20 13:13:12";

        System.out.println(DateUtils.timeDiff(time1, time2));
    }

}