package Soma.CLOVI.dto.use;

import Soma.CLOVI.domain.ShopItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
public class ShopItemDto {

    @NotNull
    private String shopName;

    @NotNull
    private String itemUrl;

    @NotNull
    private String shopLogoUrl;

    @NotNull
    private Long price;

    private String soldOutStatus;

    public static ShopItemDto createShopItemDto(ShopItem shopItem){
        ShopItemDto shopItemDto = new ShopItemDto();
        shopItemDto.setItemUrl(shopItem.getItemUrl());
        shopItemDto.setShopName(shopItem.getShop().getShopName());
        shopItemDto.setItemUrl(shopItem.getShop().getShopLogoUrl());
        shopItemDto.setShopLogoUrl(shopItem.getShop().getShopLogoUrl());
        shopItemDto.setPrice(shopItem.getPrice());
        shopItemDto.setSoldOutStatus((shopItem.getSoldOutStatus() == SoldOutStatus.Y) ? "SoldOut" : "InStock");
        return shopItemDto;
    }


}
