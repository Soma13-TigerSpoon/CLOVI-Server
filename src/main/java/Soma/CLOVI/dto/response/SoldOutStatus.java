package Soma.CLOVI.dto.response;

public enum SoldOutStatus {
  Y(true, "active"), N(false, "inactive");
  boolean status;
  String message;

  SoldOutStatus(boolean status, String message) {
    this.status = status;
    this.message = message;
  }
}
