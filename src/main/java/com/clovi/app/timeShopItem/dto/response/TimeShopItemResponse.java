package com.clovi.app.timeShopItem.dto.response;

import com.clovi.app.item.dto.response.ItemShopItemResponse;
import com.clovi.app.model.dto.response.ModelResponse;
import com.clovi.app.timeShopItem.domain.TimeShopItem;
import com.clovi.app.timeframe.domain.Timeframe;
import com.clovi.app.timeframe.dto.response.TimeframeResponse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import lombok.Getter;

@Getter
public class TimeShopItemResponse {
  private TimeframeResponse times;
  private ModelResponse model;
  private List<ItemShopItemResponse> items = new ArrayList<>();

  public TimeShopItemResponse(Timeframe timeFrame) {
    // select timeFrame
    this.times = new TimeframeResponse(timeFrame);

    // select Item
    for(TimeShopItem item : timeFrame.getItems()) {
      items.add(new ItemShopItemResponse(item.getShopItem(),item.getItem()));
    }
    items.sort(new ItemOrderComparator());

    // select model
    this.model = new ModelResponse(timeFrame.getModel());
  }
}

class ItemOrderComparator implements Comparator<ItemShopItemResponse> {
  @Override
  public int compare(ItemShopItemResponse A, ItemShopItemResponse B) {
    int orderA = A.getItem().getOrder();
    int orderB = B.getItem().getOrder();

    // No need to check if null

    return Integer.compare(orderA, orderB);
  }
}