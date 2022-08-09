package Soma.CLOVI.dto.use;

import Soma.CLOVI.domain.ShopItem;
import Soma.CLOVI.domain.item.Item;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ItemResponseDto {

    private String name;

    private String type;
    private int typeName;
    private String itemImgUrl;

    private String color;
    private String size;

    List<ShopItemResponseDto> shops = new ArrayList<>();

    public ItemResponseDto(Item item){
        this.name = item.getItemName();
        this.type = item.getItemType().toString();
        this.typeName = item.getItemType().getOrder();
        this.color = item.getColor();
        this.size = item.getSize();
        this.itemImgUrl = item.getImgUrl();
        for(ShopItem shopItem : item.getShopItems()){
            shops.add(new ShopItemResponseDto(shopItem));
        }

    }

}
