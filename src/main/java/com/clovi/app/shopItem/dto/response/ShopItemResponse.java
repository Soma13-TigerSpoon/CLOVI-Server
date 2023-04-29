package com.clovi.app.shopItem.dto.response;

import com.clovi.app.shopItem.ShopItem;
import lombok.Getter;


@Getter
public class ShopItemResponse {

  private Long id;

  private String name;

  private String shopUrl;

  private String logoUrl;

  private Long price;


  public ShopItemResponse(ShopItem shopItem) {
    this.id = shopItem.getId();
    this.name = shopItem.getShop().getName();
    this.shopUrl = shopItem.getShopItemUrl(); // Select Shop
    this.logoUrl = shopItem.getShop().getLogoUrl();
    this.price = shopItem.getPrice();
  }


}
