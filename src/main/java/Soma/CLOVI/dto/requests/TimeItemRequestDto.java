package Soma.CLOVI.dto.requests;

import Soma.CLOVI.utils.TimeFormatUtils;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TimeItemRequestDto {
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
  private String brand;
  @NotNull
  private Long categoryId;
  private boolean isWide;
  @NotNull
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
