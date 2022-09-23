package Soma.CLOVI.dto.response;

import Soma.CLOVI.domain.AffiliationLink;
import Soma.CLOVI.domain.item.Item;
import lombok.Getter;

@Getter
public class ItemAffiliationLinkResponseDto {
  private ItemResponseDto item;
  private AffiliationLinkResponseDto affiliationLink;

  public ItemAffiliationLinkResponseDto(Item item,
      AffiliationLink affiliationLink) {
    this.item = new ItemResponseDto(item);
    this.affiliationLink = affiliationLink == null ? null : new AffiliationLinkResponseDto(affiliationLink);
  }
}
