package Soma.CLOVI.dto.use;

import Soma.CLOVI.dto.requests.ShopItemRequestDto;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class TimeItemRequestDto {

  //video-data
  private Long videoId;

  //model-data
  private Long modelId;

  //time-data
  private String capturePoint;

  //item-data
  private String name;
  private int type;
  private String typeName;
  private String itemImgUrl;
  private String color;
  private String size;

  //shop-data
  private List<ShopItemRequestDto> shopItems = new ArrayList<>();
}
