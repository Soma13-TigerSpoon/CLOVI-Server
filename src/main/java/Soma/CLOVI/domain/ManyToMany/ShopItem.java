package Soma.CLOVI.domain.ManyToMany;

import Soma.CLOVI.domain.Base.BaseEntity;
import Soma.CLOVI.domain.Base.BaseTimeEntity;
import Soma.CLOVI.domain.item.Item;
import Soma.CLOVI.domain.shop.Shop;
import Soma.CLOVI.dto.use.SoldOutStatus;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShopItem extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemUrl;
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
        this.itemUrl = itemUrl;
        this.itemImgUrl = itemImgUrl;
        this.price = price;
        this.stock = stock;
        this.soldOutStatus = soldOutStatus;
        this.shop = shop;
        this.item = item;
    }


}
