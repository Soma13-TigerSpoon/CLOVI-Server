package Soma.CLOVI.dto.use;

import Soma.CLOVI.domain.ManyToMany.ShopItem;
import lombok.Getter;


@Getter
public class ShopItemResponseDto {
    private Long id;

    private String shopName;

    private String shopUrl;

    private String itemImgUrl;

    private String shopLogoUrl;

    private Long price;

    private String soldOutStatus;

    public ShopItemResponseDto(ShopItem shopItem){
        this.id = shopItem.getId();
        this.shopName = shopItem.getItemUrl();
        this.shopUrl = shopItem.getShop().getShopName();
        this.itemImgUrl = shopItem.getItemImgUrl();
        this.shopLogoUrl = shopItem.getShop().getShopLogoUrl();
        this.price = shopItem.getPrice();
        this.soldOutStatus = (shopItem.getSoldOutStatus() == SoldOutStatus.Y) ? "SoldOut" : "InStock";
    }


}
