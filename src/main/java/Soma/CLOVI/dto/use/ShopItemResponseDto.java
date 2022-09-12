package Soma.CLOVI.dto.use;

import Soma.CLOVI.domain.ManyToMany.ShopItem;
import lombok.Getter;


@Getter
public class ShopItemResponseDto {

  private Long id;

  private String name;

  private String shopUrl;

  private String itemImgUrl;

  private String logoUrl;

  private Long price;

  private String soldOutStatus;

  public ShopItemResponseDto(ShopItem shopItem) {
    this.id = shopItem.getId();
    this.name = shopItem.getShop().getName();
    this.shopUrl = shopItem.getShopUrl(); // Select Shop
    this.itemImgUrl = shopItem.getItemImgUrl();
    this.logoUrl = shopItem.getShop().getLogoUrl();
    this.price = shopItem.getPrice();
    this.soldOutStatus = (shopItem.getSoldOutStatus() == SoldOutStatus.Y) ? "SoldOut" : "InStock";
  }


}
