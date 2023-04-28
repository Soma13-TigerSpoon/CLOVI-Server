package com.clovi.app.exception.member;

import com.clovi.app.exception.BadRequestException;

public class DuplicateMemberIdException extends BadRequestException {
  private static final String SERVER_MESSAGE = "이미 존재하는 아이디 입니다.";
  private static final String ERROR_CODE = "MEMBER_ID_EXISTS";
  private static final String CLIENT_MESSAGE = "이미 존재하는 아이디 입니다. 다른 아이디를 입력해주세요!";

  public DuplicateMemberIdException() {
    super(SERVER_MESSAGE, CLIENT_MESSAGE, ERROR_CODE);
  }
}
