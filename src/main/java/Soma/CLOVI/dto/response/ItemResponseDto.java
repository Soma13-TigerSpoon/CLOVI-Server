package Soma.CLOVI.dto.response;

import Soma.CLOVI.domain.ManyToMany.ShopItem;
import Soma.CLOVI.domain.item.Item;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class ItemResponseDto {
  private Long id;
  private String name;
  private int order;
  private String itemImgUrl;
  private String color;
  private String size;
  private String brand;

  List<ShopItemResponseDto> shops = new ArrayList<>();
  List<ItemResponseDto> childItems = new ArrayList<>();

  public ItemResponseDto(Item item) {
    this.id = item.getId();
    this.name = item.getName();
    this.order = item.getCategory().getOrders();
    this.color = item.getColor();
    this.size = item.getSize();
    this.brand = item.getBrand();
    this.itemImgUrl = item.getImgUrl();
    for (ShopItem shopItem : item.getShopItems()) { // Select ShopItem
      if(shopItem.getShop().getId() != 100) { // 제휴링크가 아닌 경우에만 추가
        this.shops.add(new ShopItemResponseDto(shopItem));
      }
    }
    for (Item cur : item.getChildItems()){
      this.childItems.add(new ItemResponseDto(cur));
    }

  }

}
