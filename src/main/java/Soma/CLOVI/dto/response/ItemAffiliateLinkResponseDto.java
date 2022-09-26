package Soma.CLOVI.dto.response;

import Soma.CLOVI.domain.AffiliationLink;
import Soma.CLOVI.domain.item.Item;
import lombok.Getter;

@Getter
public class ItemAffiliateLinkResponseDto {
  private ItemResponseDto item;
  private AffiliateLinkResponseDto affiliationLink;

  public ItemAffiliateLinkResponseDto(Item item,
      AffiliationLink affiliationLink) {
    this.item = new ItemResponseDto(item);
    this.affiliationLink = affiliationLink == null ? null : new AffiliateLinkResponseDto(affiliationLink);
  }
}
