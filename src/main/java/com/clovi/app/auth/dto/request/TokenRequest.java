package com.clovi.app.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Schema(name = "토큰 재발행 요청")
@NoArgsConstructor
public class TokenRequest {
  @NotBlank(message = "refreshToken 은 필수 항목입니다!")
  @Schema(description = "로그인때 발급된 refresh 토큰")
  private String refreshToken;

  public TokenRequest(String refreshToken) {
    this.refreshToken = refreshToken;
  }

}
