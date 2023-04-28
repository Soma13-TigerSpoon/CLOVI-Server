package com.clovi.app.color.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@Schema(name = "아이템 컬러 정보 생성 요청")
@NoArgsConstructor
public class ItemColorCreateRequest {


    @NotBlank
    @Schema(description = "컬러", example = "M")
    private String color;

    @NotBlank
    @Schema(description = "선택한 이미지 ", example = "M")
    private String imgUrl;


    // 아이템 정보 저장 후 -> 색, 사이즈와 아이템 연관정보 저장
    // 이걸 다른 API를 조합해서 사용하는 이유는 나중에 테스트 코드 짤때 아이템 저장됐는지 확인하려면 아이템 이미지에 대한 저장 성공 여부도 저장해야 하기 때문에
    // 이건 크롤링 할때도 마찬가지임
}
