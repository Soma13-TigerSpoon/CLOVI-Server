package com.clovi.timeShopItem.dto.response;

import com.clovi.timeShopItem.TimeShopItem;
import com.clovi.timeframe.TimeFrame;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.clovi.item.item.dto.response.ItemShopItemResponseDto;
import com.clovi.model.dto.response.ModelResponseDto;
import com.clovi.timeframe.dto.response.TimeFrameResponseDto;
import lombok.Getter;

@Getter
public class TimeShopItemResponseDto {
  private TimeFrameResponseDto times;
  private ModelResponseDto model;
  private List<ItemShopItemResponseDto> items = new ArrayList<>();

  public TimeShopItemResponseDto(TimeFrame timeFrame) {
    // select timeFrame
    this.times = new TimeFrameResponseDto(timeFrame);

    // select Item
    for(TimeShopItem item : timeFrame.getItems()) {
      items.add(new ItemShopItemResponseDto(item.getShopItem(),item.getItem()));
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