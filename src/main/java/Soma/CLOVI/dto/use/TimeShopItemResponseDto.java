package Soma.CLOVI.dto.use;

import Soma.CLOVI.domain.ManyToMany.TimeItem;
import Soma.CLOVI.domain.TimeFrame;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class TimeShopItemResponseDto {

  private TimeResponseDto times;
  private ModelResponseDto model;
  private List<ItemResponseDto> items = new ArrayList<>();

  public TimeShopItemResponseDto(TimeFrame timeFrame) {
    this.times = new TimeResponseDto(timeFrame);
    for (TimeItem item : timeFrame.getItems()) { // Select Item
      items.add(new ItemResponseDto(item.getItem()));
    }
    this.model = new ModelResponseDto(timeFrame.getModel()); // Select Model
  }
}
