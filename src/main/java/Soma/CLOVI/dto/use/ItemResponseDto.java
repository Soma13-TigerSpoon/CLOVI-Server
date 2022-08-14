package Soma.CLOVI.dto.use;

import Soma.CLOVI.domain.ManyToMany.ShopItem;
import Soma.CLOVI.domain.item.Item;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ItemResponseDto {

    private Long id;

    private String name;

    private String typeName;
    private int type;
    private String itemImgUrl;

    private String color;
    private String size;

    List<ShopItemResponseDto> shops = new ArrayList<>();

    public ItemResponseDto(Item item){
        this.id = item.getId();
        this.name = item.getItemName();
        this.typeName = item.getItemType().toString();
        this.type = item.getItemType().getOrder();
        this.color = item.getColor();
        this.size = item.getSize();
        this.itemImgUrl = item.getImgUrl();
        for(ShopItem shopItem : item.getShopItems()){ // Select ShopItem
            this.shops.add(new ShopItemResponseDto(shopItem));
        }

    }

}
