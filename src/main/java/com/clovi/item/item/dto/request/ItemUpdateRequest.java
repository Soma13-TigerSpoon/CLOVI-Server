package com.clovi.item.item.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(name = "아이템 색상, 사이즈 수정 요청")
@NoArgsConstructor
public class ItemUpdateRequest {
  @Schema(description = "사이즈", example = "M")
  private String size;

  @Schema(description = "색상", example = "M")
  private String color;

  @Schema(description = "선택한 이미지 ", example = "M")
  private String imgUrl;
}
