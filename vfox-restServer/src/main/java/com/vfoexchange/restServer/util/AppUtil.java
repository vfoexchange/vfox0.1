package com.vfoexchange.restServer.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppUtil {


    public static String getDate(String datePattern) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String date = localDateTime.format(DateTimeFormatter.ofPattern(datePattern));
        return date;
    }
}
