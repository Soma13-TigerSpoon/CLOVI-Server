package Soma.CLOVI.domain.item;

import Soma.CLOVI.domain.Base.BaseTimeEntity;
import Soma.CLOVI.domain.ManyToMany.ShopItem;
import Soma.CLOVI.domain.ManyToMany.TimeItem;
import Soma.CLOVI.domain.ManyToMany.VideoItem;
import Soma.CLOVI.domain.TimeFrame;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(indexes = {
        @Index(name = "i_item_name", columnList = "name")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;
    private String color;
    private String size;
    private String imgUrl;
    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    //=연관관계 매핑=//
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ShopItem> shopItems = new ArrayList<>();


    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<TimeItem> times = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<VideoItem> videos = new ArrayList<>();

    @Builder
    public Item(String name, String description, String color, String size, String imgUrl, ItemType itemType) {
        this.name = name;
        this.description = description;
        this.color = color;
        this.size = size;
        this.imgUrl = imgUrl;
        this.itemType = itemType;
    }

    public void addShopItem(ShopItem shopItem){
        this.shopItems.add(shopItem);
    }

}
