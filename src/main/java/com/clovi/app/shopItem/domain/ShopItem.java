package com.clovi.app.shopItem.domain;

import com.clovi.app.base.domain.BaseEntity;
import com.clovi.app.shop.domain.Shop;
import com.clovi.app.itemInfo.domain.ItemInfo;
import com.clovi.app.shopItem.dto.request.ShopItemCreateRequest;
import com.clovi.app.shopItem.dto.request.ShopItemRequest;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.clovi.app.shopItem.dto.request.ShopItemUpdateRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShopItem extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Lob
  private String shopItemUrl;
  private Long price;

  private Long stock;

  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private Shop shop;

  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private ItemInfo itemInfo;


  @Builder
  public ShopItem(String itemUrl,Long price, Long stock,
      Shop shop, ItemInfo itemInfo) {
    this.shopItemUrl = itemUrl;
    this.price = price;
    this.stock = stock;
    this.shop = shop;
    this.itemInfo = itemInfo;
  }

  public ShopItem(ShopItemRequest shopItemRequest, ItemInfo itemInfo, Shop shop) {
    this.shopItemUrl = shopItemRequest.getShopItemUrl();
    this.price = shopItemRequest.getPrice();
    this.itemInfo = itemInfo;
    this.shop = shop;
  }


  public ShopItem(ShopItemCreateRequest shopItemCreateRequest, ItemInfo findItemInfo, Shop findShop, Long userId) {
    this.createBy = userId;
    this.shopItemUrl = shopItemCreateRequest.getShopItemUrl();
    this.price = shopItemCreateRequest.getPrice();
    this.itemInfo = findItemInfo;
    this.shop = findShop;
  }

  public void update(ShopItemUpdateRequest shopItemUpdateRequest, Shop findShop, Long userId) {
    this.lastModifiedBy = userId;
    this.shopItemUrl = shopItemUpdateRequest.getShopItemUrl();
    this.price = shopItemUpdateRequest.getPrice();
    this.shop = findShop;
  }
}
