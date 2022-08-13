package Soma.CLOVI.domain.shop;

import Soma.CLOVI.domain.Base.BaseTimeEntity;
import Soma.CLOVI.domain.ManyToMany.ShopItem;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(indexes = {
        @Index(name = "i_shop_name", columnList = "shopName")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Shop extends BaseTimeEntity {
    @Id @GeneratedValue
    private Long id;

    private String shopName;

    private String description;

    private String shopUrl;

    private String shopLogoUrl;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "shop")
    private List<ShopItem> shopItems = new ArrayList<>();

    public Shop(String shopName, String shopLogoUrl) {
        this.shopName = shopName;
        this.shopLogoUrl = shopLogoUrl;
    }
    public void addShopItem(ShopItem shopItem){
        this.shopItems.add(shopItem);
    }
}
