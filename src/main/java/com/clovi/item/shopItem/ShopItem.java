package com.clovi.item.shopItem;

import com.clovi.base.domain.BaseEntity;
import com.clovi.item.info.ItemInfo;
import com.clovi.item.shop.Shop;
import com.clovi.item.shopItem.dto.request.ShopItemCreateRequest;
import com.clovi.item.shopItem.dto.request.ShopItemRequestDto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.clovi.item.shopItem.dto.request.ShopItemUpdateRequest;
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

  public ShopItem(ShopItemRequestDto shopItemRequestDto, ItemInfo itemInfo, Shop shop) {
    this.shopItemUrl = shopItemRequestDto.getShopItemUrl();
    this.price = shopItemRequestDto.getPrice();
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
