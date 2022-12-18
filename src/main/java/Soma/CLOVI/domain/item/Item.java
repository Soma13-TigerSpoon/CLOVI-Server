package Soma.CLOVI.domain.item;

import Soma.CLOVI.domain.base.BaseEntity;
import Soma.CLOVI.domain.manytomany.ShopItem;
import Soma.CLOVI.domain.manytomany.TimeShopItem;
import Soma.CLOVI.domain.manytomany.VideoItem;
import Soma.CLOVI.domain.category.Category;
import Soma.CLOVI.dto.requests.ItemRequestDto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
public class Item extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String brand;
  private String color;
  private String size;
  @Lob
  private String imgUrl;

  //=연관관계 매핑=//

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  private Item parent;
  @OneToOne(fetch = FetchType.LAZY)
  private Category category;

  @OneToMany(mappedBy = "parent", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
  private List<Item> childItems = new ArrayList<>();
  @OneToMany(mappedBy = "item", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private List<ShopItem> shopItems = new ArrayList<>();


  @OneToMany(mappedBy = "item", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private List<TimeShopItem> times = new ArrayList<>();

  @OneToMany(mappedBy = "item", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private List<VideoItem> videoItems = new ArrayList<>();

  @Builder
  public Item(String name, String color, String size, String imgUrl) {
    this.name = name;
    this.color = color;
    this.size = size;
    this.imgUrl = imgUrl;
  }

  public Item(ItemRequestDto itemRequestDto, Category category, Item parent) {
    this.name = itemRequestDto.getName();
    this.imgUrl = itemRequestDto.getItemImgUrl();
    this.color = itemRequestDto.getColor();
    this.size = itemRequestDto.getSize();
    this.brand = itemRequestDto.getBrand();
    this.category = category;
    this.parent = parent;
  }

  public void addShopItem(ShopItem shopItem) {
    this.shopItems.add(shopItem);
  }

}
