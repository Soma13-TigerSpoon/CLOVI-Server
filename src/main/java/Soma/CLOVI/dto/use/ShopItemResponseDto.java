package Soma.CLOVI.dto.use;

import Soma.CLOVI.domain.ShopItem;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
public class ShopItemResponseDto {

    private String shopName;

    private String shopUrl;

    private String itemImgUrl;

    private String shopLogoUrl;

    private Long price;

    private String soldOutStatus;

    public ShopItemResponseDto(ShopItem shopItem){
        this.shopName = shopItem.getItemUrl();
        this.shopUrl = shopItem.getShop().getShopName();
        this.itemImgUrl = shopItem.getItemImgUrl();
        this.shopLogoUrl = shopItem.getShop().getShopLogoUrl();
        this.price = shopItem.getPrice();
        this.soldOutStatus = (shopItem.getSoldOutStatus() == SoldOutStatus.Y) ? "SoldOut" : "InStock";
    }


}
