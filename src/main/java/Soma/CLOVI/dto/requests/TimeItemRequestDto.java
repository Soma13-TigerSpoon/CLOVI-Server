package Soma.CLOVI.dto.requests;

import static Soma.CLOVI.common.common.StringTimeToLong;
import Soma.CLOVI.dto.requests.ShopItemRequestDto;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.Getter;

@Data
public class TimeItemRequestDto {

  //video-data
  private Long videoId;

  //model-data
  private Long modelId;

  //time-data
  private String startTime;

  //item-data
  private Long parentId;

  private String name;
  private int type;
  private String typeName;
  private String itemImgUrl;
  private String color;
  private String size;

  //shop-data
  private List<ShopItemRequestDto> shopItems = new ArrayList<>();

  public Long getStartTime(){
    return StringTimeToLong(this.startTime);
  }
}
