package Soma.CLOVI.domain.item;

import Soma.CLOVI.domain.Base.BaseTimeEntity;
import Soma.CLOVI.domain.ManyToMany.ShopItem;
import Soma.CLOVI.domain.ManyToMany.TimeItem;
import Soma.CLOVI.domain.ManyToMany.VideoItem;
import Soma.CLOVI.domain.TimeFrame;
import Soma.CLOVI.dto.use.TimeItemRequestDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @OneToMany(mappedBy = "item", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<ShopItem> shopItems = new ArrayList<>();


    @OneToMany(mappedBy = "item", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<TimeItem> times = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
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

    public Item(TimeItemRequestDto timeItemRequestDto){
        this.name = timeItemRequestDto.getName();
        this.imgUrl = timeItemRequestDto.getItemImgUrl();
        this.color = timeItemRequestDto.getColor();
        this.size = timeItemRequestDto.getSize();
        this.itemType = ItemType.types.get(timeItemRequestDto.getType());
    }
    public void addShopItem(ShopItem shopItem){
        this.shopItems.add(shopItem);
    }

}
