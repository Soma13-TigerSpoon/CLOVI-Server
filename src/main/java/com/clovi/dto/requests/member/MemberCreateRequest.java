package com.clovi.dto.requests.member;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(name = "회원가입 요청")
@NoArgsConstructor
public class MemberCreateRequest {
  @NotBlank
  @Size(min = 6, max = 20, message = "아이디는 3자리 이상 20자리 이하여야 합니다!")
  @Schema(description = "아이디",  example = "test123")
  private String memberId;
  @NotBlank
  @Size(min = 8, max = 20, message = "비밀번호는 8자리 이상 20자리 이하여야 합니다!")
  @Schema(description = "비밀번호",  example = "myPassword1234!")
  private String password;

  @NotBlank
  @Schema(description = "회원가입 인증키",  example = "KFODLAS52F")
  private String secretKey;


  @Builder
  public MemberCreateRequest(String memberId, String password, String secretKey) {
    this.memberId = memberId;
    this.password = password;
    this.secretKey = secretKey;
  }
}
