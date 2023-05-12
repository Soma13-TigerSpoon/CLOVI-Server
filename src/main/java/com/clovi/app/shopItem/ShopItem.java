package com.clovi.app.shopItem;

import com.clovi.app.itemInfo.ItemInfo;
import com.clovi.app.shop.Shop;
import com.clovi.app.shopItem.dto.request.ShopItemCreateRequest;
import com.clovi.app.shopItem.dto.request.ShopItemUpdateRequest;
import com.clovi.app.base.domain.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

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

  // private boolean isMain;

  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private Shop shop;

  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private ItemInfo itemInfo;

  @Builder
  public ShopItem(String shopItemUrl, Long price, Long stock, Shop shop, ItemInfo itemInfo) {
    this.shopItemUrl = shopItemUrl;
    this.price = price;
    this.stock = stock;
    this.shop = shop;
    this.itemInfo = itemInfo;
  }

  public void setShop(Shop shop) {
    this.shop = shop;
  }

  public void setItemInfo(ItemInfo itemInfo) {
    this.itemInfo = itemInfo;
  }

  public void setCreateBy(Long userId) {
    this.createBy = userId;
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
