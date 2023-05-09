package com.clovi.app.item;

import com.clovi.app.item.dto.request.ItemCreateRequest;
import com.clovi.app.item.dto.request.ItemUpdateRequest;
import com.clovi.app.base.domain.BaseEntity;
import com.clovi.app.timeShopItem.TimeShopItem;
import com.clovi.app.videoItem.VideoItem;
import com.clovi.app.itemInfo.ItemInfo;
import com.querydsl.core.annotations.QueryInit;
import lombok.AccessLevel;
import lombok.Builder;
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

  private String imgUrl;

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
    this.imgUrl = itemCreateRequest.getImgUrl();
    this.itemInfo = itemInfo;
  }

  @Builder
  public Item(Long id, String size, String color, String imgUrl, ItemInfo itemInfo, List<TimeShopItem> times, List<VideoItem> videoItems, Long userId) {
    this.id = id;
    this.size = size;
    this.color = color;
    this.imgUrl = imgUrl;
    this.itemInfo = itemInfo;
    this.times = times;
    this.videoItems = videoItems;
    this.createBy = userId;
    this.lastModifiedBy = userId;
  }

  public void update(ItemUpdateRequest itemUpdateRequest, Long userId) {
    this.lastModifiedBy = userId;
    this.color = itemUpdateRequest.getColor();
    this.size = itemUpdateRequest.getSize();
    this.imgUrl = itemUpdateRequest.getImgUrl();
  }
}
