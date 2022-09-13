package Soma.CLOVI.api.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseResponse {

  private int status;
  private ProcessStatus processStatus;
  private String message;
  private String code;
  private Object data;

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
