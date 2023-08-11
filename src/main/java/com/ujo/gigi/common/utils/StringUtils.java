package com.ujo.gigi.common.utils;

public class StringUtils {

    public static String deleteLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }

    public static boolean isEmpty(String str) {
        return str == null || str.equals("");
    }
}
