package Soma.CLOVI.domain.ManyToMany;

import Soma.CLOVI.domain.Base.BaseTimeEntity;
import Soma.CLOVI.domain.item.Item;
import Soma.CLOVI.domain.shop.Shop;
import Soma.CLOVI.dto.requests.ShopItemRequestDto;
import Soma.CLOVI.dto.response.SoldOutStatus;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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


}
