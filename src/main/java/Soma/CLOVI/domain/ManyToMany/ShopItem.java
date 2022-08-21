package Soma.CLOVI.domain.ManyToMany;

import Soma.CLOVI.domain.Base.BaseTimeEntity;
import Soma.CLOVI.domain.item.Item;
import Soma.CLOVI.domain.shop.Shop;
import Soma.CLOVI.dto.use.ShopItemRequestDto;
import Soma.CLOVI.dto.use.SoldOutStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShopItem extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String shopUrl;
    private String itemImgUrl;
    private Long price;

    private Long stock;

    private SoldOutStatus soldOutStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;


    @Builder
    public ShopItem(String itemUrl, String itemImgUrl, Long price, Long stock, SoldOutStatus soldOutStatus, Shop shop, Item item) {
        this.shopUrl = itemUrl;
        this.itemImgUrl = itemImgUrl;
        this.price = price;
        this.stock = stock;
        this.soldOutStatus = soldOutStatus;
        this.shop = shop;
        this.item = item;
    }
    public ShopItem(ShopItemRequestDto shopItemRequestDto, Item item, Shop shop){
        this.shopUrl = shopItemRequestDto.getShopUrl();
        this.itemImgUrl = shopItemRequestDto.getImgUrl();
        this.price = shopItemRequestDto.getPrice();
        this.item = item;
        this.shop = shop;
    }


}
