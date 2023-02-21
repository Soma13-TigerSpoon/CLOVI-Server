package com.clovi.dto.response;

import com.clovi.domain.shop.Shop;
import lombok.Getter;

@Getter
public class ShopResponseDto {

  private Long id;
  private String name;

  public ShopResponseDto(Shop shop) {
    this.id = shop.getId();
    this.name = shop.getName();
  }
}
