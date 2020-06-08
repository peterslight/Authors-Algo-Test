package com.peterstev.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Functions {
    public static String BASE_URL = "https://jsonmock.hackerrank.com/api/";

    public static String longToDate(Long value) {
        Date date = new Date(TimeUnit.SECONDS.toMillis(value));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY", Locale.getDefault());
        return dateFormat.format(date);
    }
}
