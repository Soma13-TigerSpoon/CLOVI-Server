package com.clovi.app.itemInfo;

import com.clovi.app.category.Category;
import com.clovi.app.itemInfo.dto.request.ItemInfoCreateRequest;
import com.clovi.app.itemInfo.dto.request.ItemInfoUpdateRequest;
import com.clovi.app.base.domain.BaseEntity;
import com.clovi.app.shopItem.ShopItem;

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
  //=연관관계 매핑=//

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  private ItemInfo parent;
  @OneToOne(fetch = FetchType.LAZY)
  private Category category;

  @OneToMany(mappedBy = "itemInfo", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private List<ShopItem> shopItems = new ArrayList<>();

  public ItemInfo(ItemInfoCreateRequest itemInfoCreateRequest, Category category, Long userId) {
    this.createBy = userId;
    this.lastModifiedBy = userId;
    this.name = itemInfoCreateRequest.getItemName();
    this.brand = itemInfoCreateRequest.getBrand();
    this.category = category;
  }

  @Builder
  public ItemInfo(Long id, String name, String brand, ItemInfo parent, Category category, List<ShopItem> shopItems, Long userId) {
    this.id = id;
    this.name = name;
    this.brand = brand;
    this.parent = parent;
    this.category = category;
    this.shopItems = shopItems;
    this.createBy = userId;
    this.lastModifiedBy = userId;
  }

  public void update(ItemInfoUpdateRequest itemInfoUpdateRequest, Category category, Long userId) {
    this.lastModifiedBy = userId;
    this.name = itemInfoUpdateRequest.getItemName();
    this.brand = itemInfoUpdateRequest.getBrand();
    this.category = category;
  }

}
