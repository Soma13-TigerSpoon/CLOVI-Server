package Soma.CLOVI.common;

public class common {

  public static Long StringTimeToLong(String time) {
    String[] timeArray = time.split(":");
    int multipleNum = 1;
    int totalSecond = 0;
    for (int i = timeArray.length - 1; i >= 0; i--) {
      totalSecond += multipleNum * Integer.parseInt(timeArray[i]);
      multipleNum *= 60;
    }
    return new Long(totalSecond);
  }
}
