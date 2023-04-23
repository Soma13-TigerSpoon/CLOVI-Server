package com.clovi.member.dto.response;

import com.clovi.member.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(name = "회원 정보 응답")
public class MemberResponse {

  @Schema(description = "회원 id")
  private Long id;
  @Schema(description = "아이디",  example = "test123")
  private String memberId;
  public MemberResponse(Member member) {
    this.id = member.getId();
    this.memberId = member.getMemberId();
  }
}
