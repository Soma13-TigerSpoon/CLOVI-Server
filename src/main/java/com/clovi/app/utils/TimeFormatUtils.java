package com.clovi.app.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeFormatUtils {
  public static Long StringTimeToLong(String time) {
    String[] timeArray = time.split(":");

    int totalSecond = 0;
    int multipleNum = 1;

    for(int i=timeArray.length-1; i>=0; i--) {
      totalSecond += multipleNum * Integer.parseInt(timeArray[i]);
      multipleNum *= 60;
    }

    return (long)totalSecond;
  }

  public static String LocalDateTimeToString(LocalDateTime localDateTime) {
    return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
  }
}
