package com.clovi.api.response;

import lombok.Getter;


@Getter
public enum MessageCode {
  //공통
  SUCCESS_GET("조회 성공", "SC001"), SUCCESS_GET_LIST("목록 조회 성공", "SC002"), SUCCESS_CREATE("저장 성공",
      "SC003"),
  SUCCESS_UPDATE("수정 성공","SC004" ), SUCCESS_DELETE("삭제 성공","SC005" ),
  SUCCESS_LOGIN("로그인 성공","SA001"),SUCCESS_GET_TOKEN("토큰 발행 성공","SA002"),
  //영상관련
  ERROR_REQ_PARAM_VIDEO_ID("존재하지 않는 영상 ID 입니다.", "EV001"),
  ERROR_REQ_PARAM_CATEGORY_ID("유효하지 않는 카테고리 ID 입니다.", "EC001"),

  ERROR_REQ_PARAM_ITEM_ID("유효하지 않는 아이템 ID 입니다.", "EI001");
  private String message;
  private String code;

  MessageCode(String message, String code) {
    this.message = message;
    this.code = code;
  }
}
