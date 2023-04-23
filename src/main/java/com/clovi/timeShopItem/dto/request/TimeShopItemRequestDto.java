package com.clovi.timeShopItem.dto.request;

import com.clovi.item.shopItem.dto.request.ShopItemRequestDto;
import com.clovi.utils.TimeFormatUtils;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TimeShopItemRequestDto {
  // video data
  @NotNull
  private Long videoId;

  // model data
  @NotNull
  private Long modelId;

  // time data
  @NotNull
  private String startTime;

  // item data
  private Long parentId;
  @NotNull
  private String name;
  @NotNull
  private String brand;
  private Long categoryId;
  private boolean isWide;

  private String itemImgUrl;
  @NotNull
  private String color;
  private String size;

  // ShopItem data
  private String affLink;
  private String affHostname;
  private Long affPrice;

  // shopItem data
  private List<ShopItemRequestDto> shopItems = new ArrayList<>();

  public Long getStartTime() {
    return TimeFormatUtils.StringTimeToLong(this.startTime);
  }
}
