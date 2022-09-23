package Soma.CLOVI.dto.requests;

import static Soma.CLOVI.common.common.StringTimeToLong;
import Soma.CLOVI.dto.requests.ShopItemRequestDto;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.Getter;

@Data
public class TimeItemRequestDto {

  //Video-data
  private Long videoId;

  //model-data
  private Long modelId;

  //time-data
  private String startTime;

  //item-data
  private Long parentId;

  private String name;
  private String brand;
  private Long categoryId;
  private boolean isWide;
  private String itemImgUrl;
  private String color;
  private String size;
  //affiliationLink-data
  private String affLink;
  private Long affPrice;

  //shop-data
  private List<ShopItemRequestDto> shopItems = new ArrayList<>();

  public Long getStartTime(){
    return StringTimeToLong(this.startTime);
  }
}
