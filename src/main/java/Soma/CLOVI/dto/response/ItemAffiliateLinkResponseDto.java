package Soma.CLOVI.dto.response;

import Soma.CLOVI.domain.AffiliateLink;
import Soma.CLOVI.domain.item.Item;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ItemAffiliateLinkResponseDto {
  private ItemResponseDto item;
  private ShopItemResponseDto affiliationLink;

  public ItemAffiliateLinkResponseDto(Item item,
      AffiliateLink affiliateLink) {
    this.item = new ItemResponseDto(item);
    this.affiliationLink = (affiliateLink == null) || (isValid(affiliateLink.getValidDate())) ? null : new ShopItemResponseDto(affiliateLink.getShopItem());
  }

  private boolean isValid(LocalDateTime localDateTime){
    return localDateTime.isBefore(LocalDateTime.now());// 유효한 기간이 남았을 경우
  }
}
