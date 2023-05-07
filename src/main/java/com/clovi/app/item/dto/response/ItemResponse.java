package com.clovi.app.item.dto.response;

import com.clovi.app.itemInfo.ItemInfo;
import com.clovi.app.item.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.clovi.app.shopItem.dto.response.ShopItemResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemResponse {
  private Long id;

  @Schema(description = "아이템 정보 ID", example = "1")
  private Long itemInfoId;

  @Schema(description = "사이즈", example = "M")
  private String size;
  @Schema(description = "브랜드", example = "어누즈")
  private String brand;
  @Schema(description = "상품명", example = "엠보 브이넥 니트")
  private String name;
  @Schema(description = "상품 이미지 링크", example = "https://image.msscdn.net/images/goods_img/20221204/2970721/2970721_1_500.jpg")
  private String imgUrl;

  @Schema(description = "상품 색", example = "black")
  private String color;

  @Schema(description = "정렬순서", example = "1")
  private int order;

  List<ShopItemResponse> shops = new ArrayList<>();
  List<ItemResponse> childItems = new ArrayList<>();
  @Builder
  public ItemResponse(Long id, Long itemInfoId, String name, int order, String imgUrl, String color, String size, String brand, List<ShopItemResponse> shops, List<ItemResponse> childItems) {
    this.id = id;
    this.itemInfoId = itemInfoId;
    this.name = name;
    this.order = order;
    this.imgUrl = imgUrl;
    this.color = color;
    this.size = size;
    this.brand = brand;
    this.shops = shops;
    this.childItems = childItems;
  }

  public static ItemResponse from(Item item){
    ItemInfo itemInfo = item.getItemInfo();
    return ItemResponse.builder()
            .id(item.getId())
            .itemInfoId(itemInfo.getId())
            .name(itemInfo.getName())
            .brand(itemInfo.getBrand())
            .imgUrl(item.getImgUrl())
            .color(item.getColor())
            .size(item.getSize())
            .order(itemInfo.getCategory().getOrders())
            .shops(itemInfo.getShopItems().stream().filter(shopItem -> shopItem.getShop().getId() != 100)
                    .map(shopItem -> ShopItemResponse.from(shopItem)).collect(Collectors.toList()))
            .build();

  }
}
