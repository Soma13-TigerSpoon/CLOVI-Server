package com.clovi.domain.item;

import com.clovi.domain.Base.BaseEntity;
import com.clovi.domain.ManyToMany.TimeShopItem;
import com.clovi.domain.ManyToMany.VideoItem;
import com.clovi.dto.requests.item.detail.ItemCreateRequest;
import com.clovi.dto.requests.item.detail.ItemUpdateRequest;
import com.querydsl.core.annotations.QueryInit;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String size;

  private String color;

  @OneToOne(fetch = FetchType.LAZY)
  @QueryInit("category")
  private ItemInfo itemInfo;

  @OneToMany(mappedBy = "item", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private List<TimeShopItem> times = new ArrayList<>();

  @OneToMany(mappedBy = "item", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private List<VideoItem> videoItems = new ArrayList<>();



  public Item(ItemCreateRequest itemCreateRequest, ItemInfo itemInfo, Long userId) {
    this.createBy = userId;
    this.lastModifiedBy = userId;
    this.size = itemCreateRequest.getSize();
    this.color = itemCreateRequest.getColor();
    this.itemInfo = itemInfo;
  }

  public void update(ItemUpdateRequest itemUpdateRequest, Long userId) {
    this.lastModifiedBy = userId;
    this.color = itemUpdateRequest.getColor();
    this.size = itemUpdateRequest.getSize();
  }
}
