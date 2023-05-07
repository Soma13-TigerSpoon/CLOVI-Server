package com.clovi.app.item.dto.response;

import com.clovi.app.shopItem.ShopItem;
import com.clovi.app.item.Item;
import com.clovi.app.shopItem.dto.response.ShopItemResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemShopItemResponse {
  private ItemResponse item;
  private ShopItemResponse shopLink;

  public ItemShopItemResponse(ShopItem shopItem, Item item) {
    this.item = ItemResponse.from(item);
    this.shopLink = (shopItem == null) ? null : ShopItemResponse.from(shopItem);
  }


  @Builder
  public ItemShopItemResponse(ItemResponse item, ShopItemResponse shopLink) {
    this.item = item;
    this.shopLink = shopLink;
  }

  public static ItemShopItemResponse from(ShopItem shopItem, Item item){
    return ItemShopItemResponse.builder()
            .item(ItemResponse.from(item))
            .shopLink(ShopItemResponse.from(shopItem))
            .build();
  }
}
