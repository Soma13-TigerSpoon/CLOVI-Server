package com.clovi.app.item.dto.response;

import com.clovi.app.shopItem.ShopItem;
import com.clovi.app.item.Item;
import com.clovi.app.shopItem.dto.response.ShopItemResponse;
import lombok.Getter;

@Getter
public class ItemShopItemResponse {
  private ItemResponse item;
  private ShopItemResponse shopLink;

  public ItemShopItemResponse(ShopItem shopItem, Item item) {
    this.item = new ItemResponse(item);
    this.shopLink = (shopItem == null) ? null : new ShopItemResponse(shopItem);
  }
}
