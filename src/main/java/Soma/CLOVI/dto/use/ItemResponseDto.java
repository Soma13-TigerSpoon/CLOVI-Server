package Soma.CLOVI.dto.use;

import Soma.CLOVI.domain.ManyToMany.ShopItem;
import Soma.CLOVI.domain.item.Item;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class ItemResponseDto {

  List<ShopItemResponseDto> shops = new ArrayList<>();
  private Long id;
  private String name;
  private String typeName;
  private int type;
  private String itemImgUrl;
  private String color;
  private String size;

  public ItemResponseDto(Item item) {
    this.id = item.getId();
    this.name = item.getName();
    this.typeName = item.getItemType().toString();
    this.type = item.getItemType().getOrder();
    this.color = item.getColor();
    this.size = item.getSize();
    this.itemImgUrl = item.getImgUrl();
    for (ShopItem shopItem : item.getShopItems()) { // Select ShopItem
      this.shops.add(new ShopItemResponseDto(shopItem));
    }

  }

}
