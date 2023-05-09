package com.clovi.app.item.dto.request;

import com.clovi.app.item.Item;
import com.clovi.app.itemInfo.ItemInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@Schema(name = "아이템 색상, 사이즈 생성 요청")
@NoArgsConstructor
public class ItemCreateRequest {

  @NotNull
  @Schema(description = "아이템 정보 ID", example = "1")
  private Long itemInfoId;

  @Schema(description = "사이즈", example = "M")
  private String size;

  @Schema(description = "상품 색", example = "black")
  private String color;

  @Schema(description = "상품 이미지 링크", example = "https://image.msscdn.net/images/goods_img/20221204/2970721/2970721_1_500.jpg")
  private String imgUrl;

  public ItemCreateRequest(Long itemInfoId, String size, String color, String imgUrl) {
    this.itemInfoId = itemInfoId;
    this.size = size;
    this.color = color;
    this.imgUrl = imgUrl;
  }

  public Item toEntity(ItemInfo itemInfo, Long userId){
    return Item.builder()
            .color(color)
            .imgUrl(imgUrl)
            .size(size)
            .itemInfo(itemInfo)
            .userId(userId)
            .build();
  }
}
