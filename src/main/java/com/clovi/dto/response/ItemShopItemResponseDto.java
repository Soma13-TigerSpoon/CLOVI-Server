package com.clovi.dto.response;

import com.clovi.domain.ManyToMany.ShopItem;
import com.clovi.domain.item.Item;
import com.clovi.domain.item.ItemInfo;
import lombok.Getter;

@Getter
public class ItemShopItemResponseDto {
  private ItemResponseDto item;
  private ShopItemResponseDto affiliationLink;

  public ItemShopItemResponseDto(ShopItem shopItem, Item item) {
    this.item = new ItemResponseDto(item);
    this.affiliationLink = (shopItem == null) ? null : new ShopItemResponseDto(shopItem);
  }
}
