package Soma.CLOVI.dto.response;

import Soma.CLOVI.domain.ManyToMany.TimeItemAffiliationLink;
import Soma.CLOVI.domain.TimeFrame;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.Getter;

@Getter
public class TimeShopItemResponseDto {
  private TimeResponseDto times;
  private ModelResponseDto model;
  private List<ItemAffiliateLinkResponseDto> items = new ArrayList<>();

  public TimeShopItemResponseDto(TimeFrame timeFrame) {
    // select timeFrame
    this.times = new TimeResponseDto(timeFrame);

    // select item
    for(TimeItemAffiliationLink item : timeFrame.getItems()) {
      items.add(new ItemAffiliateLinkResponseDto(item.getItem(),item.getAffiliateLink()));
    }
    items.sort(new ItemOrderComparator());

    // select model
    this.model = new ModelResponseDto(timeFrame.getModel());
  }
}

class ItemOrderComparator implements Comparator<ItemAffiliateLinkResponseDto> {
  @Override
  public int compare(ItemAffiliateLinkResponseDto A, ItemAffiliateLinkResponseDto B) {
    int orderA = A.getItem().getOrder();
    int orderB = B.getItem().getOrder();

    // No need to check if null

    return Integer.compare(orderA, orderB);
  }
}