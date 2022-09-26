package Soma.CLOVI.dto.response;

import Soma.CLOVI.domain.AffiliateLink;
import Soma.CLOVI.domain.item.Item;
import lombok.Getter;

@Getter
public class ItemAffiliateLinkResponseDto {
  private ItemResponseDto item;
  private AffiliateLinkResponseDto affiliationLink;

  public ItemAffiliateLinkResponseDto(Item item,
      AffiliateLink affiliateLink) {
    this.item = new ItemResponseDto(item);
    this.affiliationLink = affiliateLink == null ? null : new AffiliateLinkResponseDto(
        affiliateLink);
  }
}
