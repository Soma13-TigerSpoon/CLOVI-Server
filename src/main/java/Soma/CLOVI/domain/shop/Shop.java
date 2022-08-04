package Soma.CLOVI.domain.shop;

import Soma.CLOVI.domain.Base.BaseEntity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(indexes = {
        @Index(name = "i_shop_name", columnList = "shopName")
})
@Getter
public class Shop extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "shop_id")
    private Long id;

    private String shopName;

    private String description;

    private String shopUrl;

    private String shopLogoUrl;

    public Shop() {

    }

    @Builder
    public Shop(String shopName, String description, String shopUrl, String shopLogoUrl) {
        this.shopName = shopName;
        this.description = description;
        this.shopUrl = shopUrl;
        this.shopLogoUrl = shopLogoUrl;
    }
}
