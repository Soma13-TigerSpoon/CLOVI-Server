package com.clovi.app.timeShopItem.dto.response;

import com.clovi.app.item.dto.response.ItemShopItemResponse;
import com.clovi.app.model.dto.response.ModelResponse;
import com.clovi.app.timeframe.Timeframe;
import com.clovi.app.timeframe.dto.response.TimeframeResponse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;

@Getter
public class TimeShopItemResponse {
  private TimeframeResponse time;
  private ModelResponse model;
  private List<ItemShopItemResponse> items;

  public TimeShopItemResponse(Timeframe timeframe) {
    // select times
    this.time = new TimeframeResponse(timeframe);

    // select model
    if(timeframe.getModel() != null){
      this.model = new ModelResponse(timeframe.getModel());
    }

    // select items
    this.items = new ArrayList<>();
    if(timeframe.getItems() != null) {
      this.items = timeframe.getItems()
              .stream()
              .map((item) -> new ItemShopItemResponse(item.getShopItem(),item.getItem()))
              .sorted(new ItemOrderComparator())
              .collect(Collectors.toList());
    }
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