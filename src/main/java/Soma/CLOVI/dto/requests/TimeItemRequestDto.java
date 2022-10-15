package Soma.CLOVI.dto.requests;

import Soma.CLOVI.utils.TimeFormatUtils;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class TimeItemRequestDto {
  // video data
  private Long videoId;

  // model data
  private Long modelId;

  // time data
  private String startTime;

  // item data
  private Long parentId;

  private String name;
  private String brand;
  private Long categoryId;
  private boolean isWide;
  private String itemImgUrl;
  private String color;
  private String size;

  // affiliateLink data
  private String affLink;
  private Long affPrice;

  // shopItem data
  private List<ShopItemRequestDto> shopItems = new ArrayList<>();

  public Long getStartTime() {
    return TimeFormatUtils.StringTimeToLong(this.startTime);
  }
}
