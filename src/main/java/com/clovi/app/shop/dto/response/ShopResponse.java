package com.clovi.app.shop.dto.response;

import com.clovi.app.shop.Shop;
import lombok.Getter;

@Getter
public class ShopResponse {

  private Long id;
  private String name;

  public ShopResponse(Shop shop) {
    this.id = shop.getId();
    this.name = shop.getName();
  }
}
