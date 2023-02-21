package com.clovi.dto.requests.item.detail;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(name = "아이템 색상, 사이즈 생성 요청")
@NoArgsConstructor
public class ItemDetailCreateRequest {

  @Schema(description = "아이템 ID", example = "1")
  private Long itemId;

  @Schema(description = "색상", example = "ivory")
  private String color;

  @Schema(description = "사이즈", example = "M")
  private String size;

  @Builder
  public ItemDetailCreateRequest(Long itemId, String color, String size) {
    this.itemId = itemId;
    this.color = color;
    this.size = size;
  }
}
