package Soma.CLOVI.domain;

import Soma.CLOVI.domain.Base.BaseEntity;
import Soma.CLOVI.domain.Base.BaseTimeEntity;
import Soma.CLOVI.domain.item.Item;
import Soma.CLOVI.domain.shop.Shop;
import Soma.CLOVI.dto.use.SoldOutStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShopItem extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_item_id")
    private Long id;

    private String itemUrl;
    private String itemImgUrl;
    private Long price;

    private Long stock;

    private SoldOutStatus soldOutStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @Column(name = "time_id")
    private Long timeId;


    @Builder
    public ShopItem(String itemUrl, String itemImgUrl, Long price, Long stock, SoldOutStatus soldOutStatus, Shop shop) {
        this.itemUrl = itemUrl;
        this.itemImgUrl = itemImgUrl;
        this.price = price;
        this.stock = stock;
        this.soldOutStatus = soldOutStatus;
        this.shop = shop;
    }
}
