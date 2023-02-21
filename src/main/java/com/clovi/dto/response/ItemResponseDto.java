package com.clovi.dto.response;

import com.clovi.domain.ManyToMany.ShopItem;
import com.clovi.domain.item.Item;
import java.util.ArrayList;
import java.util.List;

import com.clovi.domain.item.ItemDetail;
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

  List<String> colorList = new ArrayList<>();
  List<String> sizeList = new ArrayList<>();


  List<ShopItemResponseDto> shops = new ArrayList<>();
  List<ItemResponseDto> childItems = new ArrayList<>();
  public ItemResponseDto(Item item) {
    this.id = item.getId();
    this.name = item.getName();
    this.order = item.getCategory().getOrders();
    this.brand = item.getBrand();
    this.itemImgUrl = item.getImgUrl();
    for (ShopItem shopItem : item.getShopItems()) { // Select ShopItem
      if(shopItem.getShop().getId() != 100) { // 제휴링크가 아닌 경우에만 추가
        this.shops.add(new ShopItemResponseDto(shopItem));
      }
    }
  }
  public ItemResponseDto(Item item, ItemDetail itemDetail) {
    this.id = item.getId();
    this.name = item.getName();
    this.order = item.getCategory().getOrders();
    this.color = itemDetail.getColor();
    this.size = itemDetail.getSize();
    this.brand = item.getBrand();
    this.itemImgUrl = item.getImgUrl();
    for (ShopItem shopItem : item.getShopItems()) { // Select ShopItem
      if(shopItem.getShop().getId() != 100) { // 제휴링크가 아닌 경우에만 추가
        this.shops.add(new ShopItemResponseDto(shopItem));
      }
    }
  }
  public ItemResponseDto(Item item, List<String> colorList, List<String> sizeList) {
    this.id = item.getId();
    this.name = item.getName();
    this.order = item.getCategory().getOrders();
    this.brand = item.getBrand();
    this.itemImgUrl = item.getImgUrl();
    this.colorList = colorList;
    this.sizeList = sizeList;
    for (ShopItem shopItem : item.getShopItems()) { // Select ShopItem
      if(shopItem.getShop().getId() != 100) { // 제휴링크가 아닌 경우에만 추가
        this.shops.add(new ShopItemResponseDto(shopItem));
      }
    }
  }
}
