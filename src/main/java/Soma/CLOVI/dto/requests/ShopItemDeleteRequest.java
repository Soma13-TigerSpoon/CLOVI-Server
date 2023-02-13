package Soma.CLOVI.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(name = "쇼핑몰 삭제 요청")
@NoArgsConstructor
public class ShopItemDeleteRequest {

  @NotNull(message = "상품 구매 링크 id는 필수 항목입니다!")
  @Schema(description = "상품 구매 링크 id", example = "1")
  private Long shopItemId;
}
