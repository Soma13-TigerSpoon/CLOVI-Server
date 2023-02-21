package com.clovi.domain.ManyToMany;

import com.clovi.domain.Base.BaseTimeEntity;
import com.clovi.domain.item.Item;
import com.clovi.domain.shop.Shop;
import com.clovi.dto.requests.ShopItemCreateRequest;
import com.clovi.dto.requests.ShopItemRequestDto;

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
public class ShopItem extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Lob
  private String shopItemUrl;
  private Long price;

  private Long stock;

  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private Shop shop;

  @ManyToOne(fetch = FetchType.LAZY)
  private Item item;


  @Builder
  public ShopItem(String itemUrl,Long price, Long stock,
      Shop shop, Item item) {
    this.shopItemUrl = itemUrl;
    this.price = price;
    this.stock = stock;
    this.shop = shop;
    this.item = item;
  }

  public ShopItem(ShopItemRequestDto shopItemRequestDto, Item item, Shop shop) {
    this.shopItemUrl = shopItemRequestDto.getShopItemUrl();
    this.price = shopItemRequestDto.getPrice();
    this.item = item;
    this.shop = shop;
  }


  public ShopItem(ShopItemCreateRequest shopItemCreateRequest, Item findItem, Shop findShop) {
    this.shopItemUrl = shopItemCreateRequest.getShopItemUrl();
    this.price = shopItemCreateRequest.getPrice();
    this.item = findItem;
    this.shop = findShop;
  }

  public void delete(){
    this.isDeleted = true;
  }
}
