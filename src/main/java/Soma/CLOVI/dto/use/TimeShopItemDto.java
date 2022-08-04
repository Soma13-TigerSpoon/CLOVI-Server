package Soma.CLOVI.dto.use;

import Soma.CLOVI.domain.Model;
import Soma.CLOVI.domain.ShopItem;
import Soma.CLOVI.domain.TimeFrame;
import Soma.CLOVI.domain.item.Item;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class TimeShopItemDto {
    private TimeDto timeDto;
    private ItemDto itemDto;
    private ModelDto modelDto;

    public TimeShopItemDto(TimeFrame timeFrame){
        timeDto = new TimeDto();
        timeDto.createTimeDto(timeFrame);
        List<ShopItem> shopItemList = timeFrame.getItemList();
        if(!shopItemList.isEmpty()){
            itemDto = new ItemDto(shopItemList.get(0).getItem(), shopItemList);
        }
        else {
            itemDto = new ItemDto();
        }
        modelDto = new ModelDto(timeFrame.getModel());
    }
}
