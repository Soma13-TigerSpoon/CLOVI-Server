package com.clovi.dto.requests.item.detail;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(name = "아이템 색상, 사이즈 수정 요청")
@NoArgsConstructor
public class ItemUpdateRequest {

  @Schema(description = "item ID", example = "1")
  private Long itemId;

  @Schema(description = "사이즈", example = "M")
  private String size;

  @Schema(description = "색상", example = "M")
  private String color;
}
