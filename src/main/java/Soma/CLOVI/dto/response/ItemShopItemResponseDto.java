package Soma.CLOVI.dto.response;

import Soma.CLOVI.domain.ManyToMany.ShopItem;
import Soma.CLOVI.domain.item.Item;
import lombok.Getter;

@Getter
public class ItemShopItemResponseDto {
  private ItemResponseDto item;
  private ShopItemResponseDto affiliationLink;

  public ItemShopItemResponseDto(Item item, ShopItem shopItem) {
    this.item = new ItemResponseDto(item);
    this.affiliationLink = (shopItem == null) ? null : new ShopItemResponseDto(shopItem);
  }
}
