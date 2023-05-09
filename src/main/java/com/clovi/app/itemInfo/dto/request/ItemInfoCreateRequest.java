package com.clovi.app.itemInfo.dto.request;

import com.clovi.app.category.Category;
import com.clovi.app.itemInfo.ItemInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(name = "아이템 생성 요청")
@NoArgsConstructor
public class ItemInfoCreateRequest {

  @NotBlank(message = "브랜드는 필수 항목입니다!")
  @Schema(description = "브랜드", example = "어누즈")
  private String brand;

  @NotBlank(message = "상품명은 필수 항목입니다!")
  @Schema(description = "상품명", example = "엠보 브이넥 니트")
  private String itemName;

  @Schema(description = "카테고리", example = "203")
  private Long categoryId;

  public ItemInfo toEntity(Category category,Long userId){
    return ItemInfo.builder()
            .brand(brand)
            .name(itemName)
            .category(category)
            .userId(userId)
            .build();
  }
}
