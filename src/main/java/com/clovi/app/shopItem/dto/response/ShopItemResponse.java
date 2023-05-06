package com.clovi.app.shopItem.dto.response;

import com.clovi.app.shopItem.ShopItem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
public class ShopItemResponse {

  private Long id;

  @Schema(description = "쇼핑몰 이름", example = "무신사")
  private String name;

  @Schema(description = "상품 구매 링크", example = "http://mss.kr/2970721")
  private String shopUrl;

  @Schema(description = "상품 가격", example = "36000")
  private Long price;

  public ShopItemResponse(ShopItem shopItem) {
    this.id = shopItem.getId();
    this.name = shopItem.getShop().getName();
    this.shopUrl = shopItem.getShopItemUrl(); // Select Shop
    this.price = shopItem.getPrice();
  }

  @Builder
  public ShopItemResponse(Long id, String name, String shopUrl, Long price) {
    this.id = id;
    this.name = name;
    this.shopUrl = shopUrl;
    this.price = price;
  }


  public static ShopItemResponse from(ShopItem shopItem) {
    return ShopItemResponse.builder()
            .id(shopItem.getId())
            .name(shopItem.getShop().getName())
            .shopUrl(shopItem.getShopItemUrl())
            .price(shopItem.getPrice())
            .build();
  }
}
