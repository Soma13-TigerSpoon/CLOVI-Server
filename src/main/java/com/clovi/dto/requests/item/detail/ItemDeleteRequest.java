package com.clovi.dto.requests.item.detail;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(name = "Item 삭제 요청")
@NoArgsConstructor
public class ItemDeleteRequest {
  @NotNull(message = "Item id는 필수 항목입니다!")
  @Schema(description = "Item id", example = "1")
  private Long itemId;
}