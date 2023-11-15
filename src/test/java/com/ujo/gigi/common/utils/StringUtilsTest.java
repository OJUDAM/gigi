package com.ujo.gigi.common.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class StringUtilsTest {

    @Test
    void parseStationCodeTest() {

        int sourceCode = Integer.parseInt("K233".replace("K", ""));
        int targetCode = Integer.parseInt("K241".replace("K", ""));

        Assertions.assertEquals(sourceCode < targetCode, true);
    }
}