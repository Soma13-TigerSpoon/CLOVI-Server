package Soma.CLOVI.dto.response;

import Soma.CLOVI.domain.ManyToMany.TimeItemAffiliationLink;
import Soma.CLOVI.domain.TimeFrame;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class TimeShopItemResponseDto {

  private TimeResponseDto times;
  private ModelResponseDto model;
  private List<ItemAffiliateLinkResponseDto> items = new ArrayList<>();

  public TimeShopItemResponseDto(TimeFrame timeFrame) {
    this.times = new TimeResponseDto(timeFrame);
    for (TimeItemAffiliationLink item : timeFrame.getItems()) { // Select Item
      items.add(new ItemAffiliateLinkResponseDto(item.getItem(),item.getAffiliateLink()));
    }
    this.model = new ModelResponseDto(timeFrame.getModel()); // Select Model
  }
}
