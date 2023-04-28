package com.clovi.app.item.dto.response;

import com.clovi.app.item.domain.Item;
import com.clovi.app.shopItem.domain.ShopItem;
import com.clovi.app.shopItem.dto.response.ShopItemResponse;
import lombok.Getter;

@Getter
public class ItemShopItemResponse {
  private ItemResponse item;
  private ShopItemResponse affiliationLink;

  public ItemShopItemResponse(ShopItem shopItem, Item item) {
    this.item = new ItemResponse(item);
    this.affiliationLink = (shopItem == null) ? null : new ShopItemResponse(shopItem);
  }
}
