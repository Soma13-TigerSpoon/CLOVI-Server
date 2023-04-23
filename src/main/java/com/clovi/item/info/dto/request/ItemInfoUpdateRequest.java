package com.clovi.item.info.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(name = "아이템 수정 요청")
@NoArgsConstructor
public class ItemInfoUpdateRequest {

  @NotBlank(message = "브랜드는 필수 항목입니다!")
  @Schema(description = "브랜드", example = "어누즈")
  private String brand;

  @NotBlank(message = "상품명은 필수 항목입니다!")
  @Schema(description = "상품명", example = "엠보 브이넥 니트")
  private String itemName;

  @NotBlank(message = "상품 이미지 링크는 필수 항목입니다!")
  @Schema(description = "상품 이미지 링크", example = "https://image.msscdn.net/images/goods_img/20221204/2970721/2970721_1_500.jpg")
  private String itemImgUrl;

  @Schema(description = "상품 색", example = "black")
  private String color;

  @Schema(description = "카테고리", example = "203")
  private Long categoryId;
}
