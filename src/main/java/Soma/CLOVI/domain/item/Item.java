package Soma.CLOVI.domain.item;

import Soma.CLOVI.domain.Base.BaseTimeEntity;
import Soma.CLOVI.domain.ManyToMany.ShopItem;
import Soma.CLOVI.domain.ManyToMany.VideoItem;
import Soma.CLOVI.domain.TimeFrame;
import Soma.CLOVI.domain.youtube.Video;
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
    private Long id;

    private String itemName;

    private String description;
    private String color;
    private String size;
    private String imgUrl;
    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    //=연관관계 매핑=//
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ShopItem> shopItems = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    private TimeFrame timeFrame;

    @OneToMany(mappedBy = "item")
    private List<VideoItem> videoItems = new ArrayList<>();

    @Builder
    public Item(String itemName, String description, String color, String size, String imgUrl, ItemType itemType, TimeFrame timeFrame) {
        this.itemName = itemName;
        this.description = description;
        this.color = color;
        this.size = size;
        this.imgUrl = imgUrl;
        this.itemType = itemType;
        this.timeFrame = timeFrame;
    }

    public void addShopItem(ShopItem shopItem){
        this.shopItems.add(shopItem);
    }
}
