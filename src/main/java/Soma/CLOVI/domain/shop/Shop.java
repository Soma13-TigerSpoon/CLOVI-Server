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
        @Index(name = "i_shop_name", columnList = "name")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Shop extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String url;

    private String logoUrl;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "shop")
    private List<ShopItem> shopItems = new ArrayList<>();

    public Shop(String name, String logoUrl) {
        this.name = name;
        this.logoUrl = logoUrl;
    }
    public void addShopItem(ShopItem shopItem){
        this.shopItems.add(shopItem);
    }
}
