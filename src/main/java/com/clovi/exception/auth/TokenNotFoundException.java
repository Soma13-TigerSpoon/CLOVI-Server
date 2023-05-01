package com.clovi.exception.auth;
public class TokenNotFoundException extends UnauthorizedException {
  private static final String ERROR_CODE = "REFRESH_TOKEN_NOT_FOUND";
  private static final String SERVER_MESSAGE = "존재하지 않는 토큰 조회";
  private static final String CLIENT_MESSAGE = "해당 토큰 값을 찾지 못했습니다.";

  public TokenNotFoundException(final Long id) {
    super(String.format("%s -> member id: %d", SERVER_MESSAGE, id), CLIENT_MESSAGE, ERROR_CODE);
  }
}
