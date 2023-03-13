package com.clovi.domain.item;

import com.clovi.domain.Base.BaseEntity;
import com.clovi.domain.ManyToMany.ShopItem;
import com.clovi.domain.category.Category;
import com.clovi.dto.requests.item.ItemInfoCreateRequest;
import com.clovi.dto.requests.item.ItemInfoUpdateRequest;
import com.clovi.dto.requests.TimeItemRequestDto;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(indexes = {
    @Index(name = "i_item_name", columnList = "name")
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemInfo extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String brand;
  @Lob
  private String imgUrl;

  private String color;

  private Integer countOfColors;

  //=연관관계 매핑=//

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  private ItemInfo parent;
  @OneToOne(fetch = FetchType.LAZY)
  private Category category;

  @OneToMany(mappedBy = "itemInfo", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private List<ShopItem> shopItems = new ArrayList<>();

  @Builder
  public ItemInfo(String name, String color, String imgUrl) {
    this.color = color;
    this.name = name;
    this.imgUrl = imgUrl;
  }

  public ItemInfo(TimeItemRequestDto timeItemRequestDto, Category category, ItemInfo parent) {
    this.name = timeItemRequestDto.getName();
    this.imgUrl = timeItemRequestDto.getItemImgUrl();
    this.brand = timeItemRequestDto.getBrand();
    this.category = category;
    this.parent = parent;
    this.color = timeItemRequestDto.getColor();
  }

  public ItemInfo(ItemInfoCreateRequest itemInfoCreateRequest, Category category, Long userId) {
    this.createBy = userId;
    this.lastModifiedBy = userId;
    this.name = itemInfoCreateRequest.getItemName();
    this.brand = itemInfoCreateRequest.getBrand();
    this.category = category;
    this.color = itemInfoCreateRequest.getColor();
  }
  public void update(ItemInfoUpdateRequest itemInfoUpdateRequest, Category category, Long userId) {
    this.lastModifiedBy = userId;
    this.name = itemInfoUpdateRequest.getItemName();
    this.brand = itemInfoUpdateRequest.getBrand();
    this.category = category;
    this.color = itemInfoUpdateRequest.getColor();
  }

    public void addShopItem(ShopItem shopItem) {
      this.shopItems.add(shopItem);
    }
}
