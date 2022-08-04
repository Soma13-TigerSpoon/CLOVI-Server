package Soma.CLOVI.domain;

import Soma.CLOVI.domain.Base.BaseEntity;
import Soma.CLOVI.domain.Base.BaseTimeEntity;
import Soma.CLOVI.domain.item.Item;
import Soma.CLOVI.domain.shop.Shop;
import Soma.CLOVI.dto.use.SoldOutStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class ShopItem extends BaseTimeEntity {
    @Id @GeneratedValue
    @Column(name = "shop_item_id")
    private Long id;

    private String itemUrl;
    private String itemImgUrl;
    private Long price;

    private Long stock;

    private SoldOutStatus soldOutStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @Column(name = "time_id")
    private Long timeId;


    public ShopItem(){
    }

    @Builder
    public ShopItem(String itemUrl, String itemImgUrl, Long price, Long stock, SoldOutStatus soldOutStatus, Item item, Shop shop) {
        this.itemUrl = itemUrl;
        this.itemImgUrl = itemImgUrl;
        this.price = price;
        this.stock = stock;
        this.soldOutStatus = soldOutStatus;
        this.item = item;
        this.shop = shop;
    }
}
