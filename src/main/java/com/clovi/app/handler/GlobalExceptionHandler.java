package com.clovi.app.handler;

import com.clovi.app.base.dto.response.ErrorResponse;
import com.clovi.app.exception.BadRequestException;
import com.clovi.app.exception.auth.NoPermissionException;
import com.clovi.app.exception.auth.UnauthorizedException;
import java.security.InvalidParameterException;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ErrorResponse> handleArgumentMismatchException(MethodArgumentTypeMismatchException e){
    log.error("MethodArgumentTypeMismatchException 발생!!! trace:{}", e.getStackTrace());
    final ErrorResponse response = ErrorResponse.builder()
            .status(HttpStatus.BAD_REQUEST.value())
            .message(e.getMessage()).build();
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  /**
   * @valid  유효성체크에 통과하지 못하면  MethodArgumentNotValidException 이 발생한다.
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleArgumentValidException(MethodArgumentNotValidException e){
    log.error("MethodArgumentNotValidException 발생!!! trace:{}",e.getStackTrace());
    final ErrorResponse response = ErrorResponse.builder()
            .status(HttpStatus.BAD_REQUEST.value())
            .message(e.getMessage()).build();
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
    log.error("METHOD_NOT_ALLOWED 발생!!! url:{}, trace:{}",request.getRequestURI(), e.getStackTrace());
    final ErrorResponse response = ErrorResponse.builder()
        .status(HttpStatus.METHOD_NOT_ALLOWED.value())
        .message(e.getMessage()).build();

    return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
  }

  //@Validated 검증 실패 시 Catch
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(InvalidParameterException.class)
  protected ResponseEntity<ErrorResponse> handleInvalidParameterException(InvalidParameterException e, HttpServletRequest request) {
    log.error("InvalidParameterException 발생!!! url:{}, trace:{}",request.getRequestURI(), e.getStackTrace());
    ErrorResponse response = ErrorResponse.builder()
        .status(HttpStatus.BAD_REQUEST.value())
        .message(e.toString()).build();

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({
      BadRequestException.class
  })
  public ResponseEntity<ErrorResponse> handleBadRequestException(final BadRequestException e, HttpServletRequest request) {
    log.error("BadRequestException 발생!!! url:{}, trace:{}",request.getRequestURI(), e.getStackTrace());
    ErrorResponse response = ErrorResponse.builder()
        .status(HttpStatus.BAD_REQUEST.value())
        .message(e.getClientMessage())
        .code(e.getErrorCode()).build();
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }


  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler({
      UnauthorizedException.class
  })
  public ResponseEntity<ErrorResponse> handleInvalidAuthorization(final UnauthorizedException e, HttpServletRequest request) {

    log.error("UnauthorizedException 발생!!! url:{}, trace:{}",request.getRequestURI(), e.getStackTrace());

    ErrorResponse response = ErrorResponse.builder()
        .status(HttpStatus.UNAUTHORIZED.value())
        .message(e.getClientMessage())
        .code(e.getErrorCode()).build();

    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
  }

  @ResponseStatus(HttpStatus.FORBIDDEN)
  @ExceptionHandler({
      NoPermissionException.class
  })
  public ResponseEntity<ErrorResponse> handleNoPermission(final NoPermissionException e, HttpServletRequest request) {

    log.error("NoPermissionException 발생!!! url:{}, trace:{}",request.getRequestURI(), e.getStackTrace());

    ErrorResponse response = ErrorResponse.builder()
        .status(HttpStatus.FORBIDDEN.value())
        .message(e.getClientMessage())
        .code(e.getErrorCode()).build();

    return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler
  public void handleRuntimeException(final RuntimeException e, HttpServletRequest request) {
    log.error("예상하지 못한 에러가 발생하였습니다. 요청 url : %s", request.getRequestURI() , e);
  }
}
