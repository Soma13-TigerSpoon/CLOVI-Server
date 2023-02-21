package com.clovi.api.response;

import lombok.Getter;

@Getter
public class BaseResponse {

  private int status;
  private ProcessStatus processStatus;
  private String message;
  private String code;
  private Object data;

  public BaseResponse(Object body, int status, MessageCode messageCode) {
    this.data = body;
    this.status = status;
    this.message = messageCode.getMessage();
    this.code = messageCode.getCode();
  }
  public BaseResponse(int status, ProcessStatus processStatus, MessageCode messageCode) {
    this.status = status;
    this.processStatus = processStatus;
    this.message = messageCode.getMessage();
    this.code = messageCode.getCode();
  }

  public BaseResponse(Object body, int status, ProcessStatus processStatus,
      MessageCode messageCode) {
    this.data = body;
    this.status = status;
    this.processStatus = processStatus;
    this.message = messageCode.getMessage();
    this.code = messageCode.getCode();
  }

}
