package com.clovi.app.itemInfo.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

//누가 할 수 있게 하는게 맞을까 이거 아이템 자체는 삭제 못하게 하고 아이템과 보여주는 시간의 연관관계 테이블에서만 삭제 되도록 해야 할 거 같은데 ㅇ
@Getter
@Schema(name = "아이템 삭제 요청")
@NoArgsConstructor
public class ItemInfoDeleteRequest {

  @NotNull(message = "아이템 id는 필수 항목입니다!")
  @Schema(description = "아이템 id", example = "1")
  private Long itemId;
}
