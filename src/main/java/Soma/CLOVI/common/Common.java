package Soma.CLOVI.common;

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
}
