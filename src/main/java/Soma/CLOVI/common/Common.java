package Soma.CLOVI.common;

import javax.servlet.http.HttpServletRequest;

public class Common {
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

  public static String getClientIP(HttpServletRequest request) {
    String ip = request.getHeader("X-Forwarded-For");

    if (ip == null) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null) {
      ip = request.getHeader("HTTP_CLIENT_IP");
    }
    if (ip == null) {
      ip = request.getHeader("HTTP_X_FORWARDED_FOR");
    }
    if (ip == null) {
      ip = request.getRemoteAddr();
    }
    return ip;
  }
}
