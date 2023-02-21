package com.clovi.dto.requests.item.detail;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(name = "ItemDetail 삭제 요청")
@NoArgsConstructor
public class ItemDetailDeleteRequest {
  @NotNull(message = "ItemDetail id는 필수 항목입니다!")
  @Schema(description = "ItemDetail id", example = "1")
  private Long itemDetailId;
}