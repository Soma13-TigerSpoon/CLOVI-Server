package Soma.CLOVI.dto.use;

import Soma.CLOVI.domain.ShopItem;
import Soma.CLOVI.domain.item.Item;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ItemDto {

    private String itemName;
    private String itemType;
    private String itemImgUrl;

    private String color;
    private String size;

    List<ShopItemDto> shopItemDtoList = new ArrayList<>();

    public ItemDto(Item item, List<ShopItem> shopItems){
        itemName = item.getItemName();
        itemType = item.getItemType().name();
        color = item.getColor();
        size = item.getSize();
        if(!shopItems.isEmpty()){
            itemImgUrl = shopItems.get(0).getItemImgUrl();
            for(ShopItem shopItem : shopItems){
                shopItemDtoList.add(ShopItemDto.createShopItemDto(shopItem));
            }
        }

    }

}
