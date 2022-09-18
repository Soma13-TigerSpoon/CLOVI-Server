package Soma.CLOVI.domain.item;

import Soma.CLOVI.domain.Base.BaseTimeEntity;
import Soma.CLOVI.domain.ManyToMany.ShopItem;
import Soma.CLOVI.domain.ManyToMany.TimeItem;
import Soma.CLOVI.domain.ManyToMany.VideoItem;
import Soma.CLOVI.dto.use.TimeItemRequestDto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(indexes = {
    @Index(name = "i_item_name", columnList = "name")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String description;
  private String color;
  private String size;
  @Lob
  private String imgUrl;
  @Enumerated(EnumType.STRING)
  private ItemType itemType;

  //=연관관계 매핑=//
  @OneToMany(mappedBy = "item", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private List<ShopItem> shopItems = new ArrayList<>();


  @OneToMany(mappedBy = "item", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private List<TimeItem> times = new ArrayList<>();

  @OneToMany(mappedBy = "item", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private List<VideoItem> videos = new ArrayList<>();

  @Builder
  public Item(String name, String description, String color, String size, String imgUrl,
      ItemType itemType) {
    this.name = name;
    this.description = description;
    this.color = color;
    this.size = size;
    this.imgUrl = imgUrl;
    this.itemType = itemType;
  }

  public Item(TimeItemRequestDto timeItemRequestDto) {
    this.name = timeItemRequestDto.getName();
    this.imgUrl = timeItemRequestDto.getItemImgUrl();
    this.color = timeItemRequestDto.getColor();
    this.size = timeItemRequestDto.getSize();
    this.itemType = ItemType.types.get(timeItemRequestDto.getType());
  }

  public void addShopItem(ShopItem shopItem) {
    this.shopItems.add(shopItem);
  }

}
