package com.clovi.member;

import com.clovi.base.dto.response.BaseResponse;
import com.clovi.base.dto.response.MessageCode;
import com.clovi.member.dto.request.MemberCreateRequest;
import com.clovi.member.dto.response.MemberResponse;
import com.clovi.base.dto.response.SavedId;
import com.clovi.auth.support.AuthMember;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name =  "회원 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MemberController {

  private final MemberService memberService;

  @PostMapping("/register")
  @Operation(summary = "회원가입", description = "회원가입에 필요한 데이터로회원가입을 한다.", responses = {
      @ApiResponse(responseCode = "201", description = "회원가입 성공", content = @Content(schema = @Schema(implementation = SavedId.class)))
  })
  public ResponseEntity saveMember(@RequestBody @Validated MemberCreateRequest memberCreateRequest){
    SavedId savedId = new SavedId(memberService.register(memberCreateRequest));
    return ResponseEntity.created(URI.create("/api/v1/member/" + savedId.getId())).body(new BaseResponse(savedId,HttpStatus.CREATED.value(),
        MessageCode.SUCCESS_CREATE));
  }

  @GetMapping("/member")
  @Operation(summary = "회원 조회", description = "Access Token 으로 회원을 조회한다.", responses = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = MemberResponse.class)))
  })
  public ResponseEntity findById(@AuthMember Member member){
    MemberResponse memberResponse = new MemberResponse(member);
    return ResponseEntity.ok(new BaseResponse(memberResponse, HttpStatus.OK.value(), MessageCode.SUCCESS_GET));
  }

}
