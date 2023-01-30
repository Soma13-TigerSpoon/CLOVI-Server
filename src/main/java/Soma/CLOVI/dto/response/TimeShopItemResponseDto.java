package Soma.CLOVI.dto.response;

import Soma.CLOVI.domain.ManyToMany.TimeShopItem;
import Soma.CLOVI.domain.TimeFrame;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.Getter;

@Getter
public class TimeShopItemResponseDto {
  private TimeResponseDto times;
  private ModelResponseDto model;
  private List<ItemShopItemResponseDto> items = new ArrayList<>();

  public TimeShopItemResponseDto(TimeFrame timeFrame) {
    // select timeFrame
    this.times = new TimeResponseDto(timeFrame);

    // select item
    for(TimeShopItem item : timeFrame.getItems()) {
      items.add(new ItemShopItemResponseDto(item.getItem(),item.getShopItem()));
    }
    items.sort(new ItemOrderComparator());

    // select model
    this.model = new ModelResponseDto(timeFrame.getModel());
  }
}

class ItemOrderComparator implements Comparator<ItemShopItemResponseDto> {
  @Override
  public int compare(ItemShopItemResponseDto A, ItemShopItemResponseDto B) {
    int orderA = A.getItem().getOrder();
    int orderB = B.getItem().getOrder();

    // No need to check if null

    return Integer.compare(orderA, orderB);
  }
}