package Soma.CLOVI.dto.response;

import Soma.CLOVI.domain.item.Item;
import Soma.CLOVI.domain.manytomany.ShopItem;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ItemAffiliateLinkResponseDto {
  private ItemResponseDto item;
  private ShopItemResponseDto affiliationLink;

  public ItemAffiliateLinkResponseDto(Item item,
      ShopItem shopItem) {
    this.item = new ItemResponseDto(item);
    this.affiliationLink = (shopItem == null) ? null : new ShopItemResponseDto(
        shopItem);
  }

  private boolean isValid(LocalDateTime localDateTime){
    return localDateTime.isBefore(LocalDateTime.now());// 유효한 기간이 남았을 경우
  }
}
