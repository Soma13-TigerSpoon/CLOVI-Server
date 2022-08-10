package Soma.CLOVI.domain.shop;

import Soma.CLOVI.domain.Base.BaseEntity;
import Soma.CLOVI.domain.Base.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(indexes = {
        @Index(name = "i_shop_name", columnList = "shopName")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Shop extends BaseTimeEntity {
    @Id @GeneratedValue
    @Column(name = "shop_id")
    private Long id;

    private String shopName;

    private String description;

    private String shopUrl;

    private String shopLogoUrl;


    @Builder
    public Shop(String shopName, String description, String shopUrl, String shopLogoUrl) {
        this.shopName = shopName;
        this.description = description;
        this.shopUrl = shopUrl;
        this.shopLogoUrl = shopLogoUrl;
    }
}
