package com.clovi.item.shopItem.dto.response;

import com.clovi.item.shopItem.ShopItem;
import lombok.Getter;


@Getter
public class ShopItemResponseDto {

  private Long id;

  private String name;

  private String shopUrl;

  private String logoUrl;

  private Long price;


  public ShopItemResponseDto(ShopItem shopItem) {
    this.id = shopItem.getId();
    this.name = shopItem.getShop().getName();
    this.shopUrl = shopItem.getShopItemUrl(); // Select Shop
    this.logoUrl = shopItem.getShop().getLogoUrl();
    this.price = shopItem.getPrice();
  }


}
