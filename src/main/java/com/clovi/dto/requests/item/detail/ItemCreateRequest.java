package com.clovi.dto.requests.item.detail;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(name = "아이템 색상, 사이즈 생성 요청")
@NoArgsConstructor
public class ItemCreateRequest {

  @Schema(description = "아이템 ID", example = "1")
  private Long itemId;

  @Schema(description = "사이즈", example = "M")
  private String size;
  @Schema(description = "컬러", example = "M")
  private String color;

  @Builder
  public ItemCreateRequest(Long itemId, String size, String color) {
    this.itemId = itemId;
    this.size = size;
    this.color = color;
  }
}
