package com.clovi.app.item.dto.response;

import com.clovi.app.shopItem.ShopItem;
import com.clovi.app.itemInfo.ItemInfo;
import com.clovi.app.item.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.clovi.app.shopItem.dto.response.ShopItemResponse;
import com.clovi.app.color.dto.response.ColorAndSizeResponse;
import lombok.Getter;

@Getter
public class ItemResponse {
  private Long id;
  private String name;
  private int order;
  private String itemImgUrl;
  private String color;
  private String size;
  private String brand;

  List<String> colorList = new ArrayList<>();
  List<String> sizeList = new ArrayList<>();

  List<ColorAndSizeResponse> colorAndSizeList = new ArrayList<>();
  List<ShopItemResponse> shops = new ArrayList<>();
  List<ItemResponse> childItems = new ArrayList<>();
  public ItemResponse(ItemInfo itemInfo) {
    this.id = itemInfo.getId();
    this.name = itemInfo.getName();
    this.order = itemInfo.getCategory().getOrders();
    this.brand = itemInfo.getBrand();
    this.itemImgUrl = itemInfo.getImgUrl();
    for (ShopItem shopItem : itemInfo.getShopItems()) { // Select ShopItem
      if(shopItem.getShop().getId() != 100) { // 제휴링크가 아닌 경우에만 추가
        this.shops.add(new ShopItemResponse(shopItem));
      }
    }
  }
  public ItemResponse(Item item) {
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
        this.shops.add(new ShopItemResponse(shopItem));
      }
    }
  }
  public ItemResponse(ItemInfo itemInfo, List<String> colorList, List<String> sizeList) {
    this.id = itemInfo.getId();
    this.name = itemInfo.getName();
    this.order = itemInfo.getCategory().getOrders();
    this.brand = itemInfo.getBrand();
    this.itemImgUrl = itemInfo.getImgUrl();
    this.colorList = colorList;
    this.sizeList = sizeList;
    for (ShopItem shopItem : itemInfo.getShopItems()) { // Select ShopItem
      if(shopItem.getShop().getId() != 100) { // 제휴링크가 아닌 경우에만 추가
        this.shops.add(new ShopItemResponse(shopItem));
      }
    }
  }

  public ItemResponse(ItemInfo itemInfo, Item item) {
    this.id = itemInfo.getId();
    this.name = itemInfo.getName();
    this.color = item.getColor();
    this.size = item.getSize();
    this.order = itemInfo.getCategory().getOrders();
    this.brand = itemInfo.getBrand();
    this.itemImgUrl = itemInfo.getImgUrl();
    for (ShopItem shopItem : itemInfo.getShopItems()) { // Select ShopItem
      if(shopItem.getShop().getId() != 100) { // 제휴링크가 아닌 경우에만 추가
        this.shops.add(new ShopItemResponse(shopItem));
      }
    }
  }
  public ItemResponse(ItemInfo itemInfo, List<Item> item) {
    this.id = itemInfo.getId();
    this.name = itemInfo.getName();
    this.order = itemInfo.getCategory().getOrders();
    this.brand = itemInfo.getBrand();
    this.itemImgUrl = itemInfo.getImgUrl();
    for (ShopItem shopItem : itemInfo.getShopItems()) { // Select ShopItem
      if(shopItem.getShop().getId() != 100) { // 제휴링크가 아닌 경우에만 추가
        this.shops.add(new ShopItemResponse(shopItem));
      }
    }
    this.colorAndSizeList  = item.stream().map(ColorAndSizeResponse::new).collect(Collectors.toList());
  }
}
