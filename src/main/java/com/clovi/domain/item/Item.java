package com.clovi.domain.item;

import com.clovi.domain.Base.BaseEntity;
import com.clovi.domain.ManyToMany.ShopItem;
import com.clovi.domain.ManyToMany.TimeShopItem;
import com.clovi.domain.ManyToMany.VideoItem;
import com.clovi.domain.category.Category;
import com.clovi.dto.requests.item.ItemCreateRequest;
import com.clovi.dto.requests.item.ItemUpdateRequest;
import com.clovi.dto.requests.TimeItemRequestDto;
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
    this.imgUrl = imgUrl;
  }

  public Item(TimeItemRequestDto timeItemRequestDto, Category category, Item parent) {
    this.name = timeItemRequestDto.getName();
    this.imgUrl = timeItemRequestDto.getItemImgUrl();
    this.brand = timeItemRequestDto.getBrand();
    this.category = category;
    this.parent = parent;
  }

  public Item(ItemCreateRequest itemCreateRequest, Category category, Long userId) {
    this.createBy = userId;
    this.lastModifiedBy = userId;
    this.name = itemCreateRequest.getItemName();
    this.brand = itemCreateRequest.getBrand();
    this.category = category;
  }


  public void addShopItem(ShopItem shopItem) {
    this.shopItems.add(shopItem);
  }

  public void update(ItemUpdateRequest itemUpdateRequest, Category category, Long userId) {
    this.lastModifiedBy = userId;
    this.name = itemUpdateRequest.getItemName();
    this.brand = itemUpdateRequest.getBrand();
    this.category = category;
  }
  public void delete(){
    this.isDeleted = true;
  }
}
