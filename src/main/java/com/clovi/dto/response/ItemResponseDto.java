package com.clovi.dto.response;

import com.clovi.domain.ManyToMany.ShopItem;
import com.clovi.domain.item.ItemInfo;
import com.clovi.domain.item.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

  List<ColorAndSizeResponseDto> colorAndSizeList = new ArrayList<>();
  List<ShopItemResponseDto> shops = new ArrayList<>();
  List<ItemResponseDto> childItems = new ArrayList<>();
  public ItemResponseDto(ItemInfo itemInfo) {
    this.id = itemInfo.getId();
    this.name = itemInfo.getName();
    this.order = itemInfo.getCategory().getOrders();
    this.brand = itemInfo.getBrand();
    this.itemImgUrl = itemInfo.getImgUrl();
    for (ShopItem shopItem : itemInfo.getShopItems()) { // Select ShopItem
      if(shopItem.getShop().getId() != 100) { // 제휴링크가 아닌 경우에만 추가
        this.shops.add(new ShopItemResponseDto(shopItem));
      }
    }
  }
  public ItemResponseDto(Item item) {
    ItemInfo itemInfo = item.getItemInfo();
    this.id = itemInfo.getId();
    this.name = itemInfo.getName();
    this.order = itemInfo.getCategory().getOrders();
    this.color = item.getColor();
    this.size = item.getSize();
    this.brand = itemInfo.getBrand();
    this.itemImgUrl = itemInfo.getImgUrl();
    for (ShopItem shopItem : itemInfo.getShopItems()) { // Select ShopItem
      if(shopItem.getShop().getId() != 100) { // 제휴링크가 아닌 경우에만 추가
        this.shops.add(new ShopItemResponseDto(shopItem));
      }
    }
  }
  public ItemResponseDto(ItemInfo itemInfo, List<String> colorList, List<String> sizeList) {
    this.id = itemInfo.getId();
    this.name = itemInfo.getName();
    this.order = itemInfo.getCategory().getOrders();
    this.brand = itemInfo.getBrand();
    this.itemImgUrl = itemInfo.getImgUrl();
    this.colorList = colorList;
    this.sizeList = sizeList;
    for (ShopItem shopItem : itemInfo.getShopItems()) { // Select ShopItem
      if(shopItem.getShop().getId() != 100) { // 제휴링크가 아닌 경우에만 추가
        this.shops.add(new ShopItemResponseDto(shopItem));
      }
    }
  }

  public ItemResponseDto(ItemInfo itemInfo, Item item) {
    this.id = itemInfo.getId();
    this.name = itemInfo.getName();
    this.color = item.getColor();
    this.size = item.getSize();
    this.order = itemInfo.getCategory().getOrders();
    this.brand = itemInfo.getBrand();
    this.itemImgUrl = itemInfo.getImgUrl();
    for (ShopItem shopItem : itemInfo.getShopItems()) { // Select ShopItem
      if(shopItem.getShop().getId() != 100) { // 제휴링크가 아닌 경우에만 추가
        this.shops.add(new ShopItemResponseDto(shopItem));
      }
    }
  }
  public ItemResponseDto(ItemInfo itemInfo, List<Item> item) {
    this.id = itemInfo.getId();
    this.name = itemInfo.getName();
    this.order = itemInfo.getCategory().getOrders();
    this.brand = itemInfo.getBrand();
    this.itemImgUrl = itemInfo.getImgUrl();
    for (ShopItem shopItem : itemInfo.getShopItems()) { // Select ShopItem
      if(shopItem.getShop().getId() != 100) { // 제휴링크가 아닌 경우에만 추가
        this.shops.add(new ShopItemResponseDto(shopItem));
      }
    }
    this.colorAndSizeList  = item.stream().map(ColorAndSizeResponseDto::new).collect(Collectors.toList());
  }
}
