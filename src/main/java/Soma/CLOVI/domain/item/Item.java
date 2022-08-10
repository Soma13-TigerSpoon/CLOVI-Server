package Soma.CLOVI.domain.item;

import Soma.CLOVI.domain.Base.BaseTimeEntity;
import Soma.CLOVI.domain.ShopItem;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(indexes = {
        @Index(name = "i_item_name", columnList = "itemName")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseTimeEntity {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String itemName;

    private String description;
    private String color;
    private String size;
    private String imgUrl;
    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    //=연관관계 매핑=//
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private List<ShopItem> shopItems = new ArrayList<>();

    @Builder
    public Item(String itemName, String description, String color, String size, ItemType itemType, String imgUrl) {
        this.itemName = itemName;
        this.description = description;
        this.color = color;
        this.size = size;
        this.itemType = itemType;
        this.imgUrl = imgUrl;
    }

    public void addShopItem(ShopItem shopItem){
        this.shopItems.add(shopItem);
    }
}
