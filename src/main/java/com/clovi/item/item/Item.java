package com.clovi.item.item;

import com.clovi.base.domain.BaseEntity;
import com.clovi.timeShopItem.TimeShopItem;
import com.clovi.videoitem.VideoItem;
import com.clovi.item.info.ItemInfo;
import com.clovi.item.item.dto.request.ItemCreateRequest;
import com.clovi.item.item.dto.request.ItemUpdateRequest;
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

  public void update(ItemUpdateRequest itemUpdateRequest, Long userId) {
    this.lastModifiedBy = userId;
    this.color = itemUpdateRequest.getColor();
    this.size = itemUpdateRequest.getSize();
    this.imgUrl = itemUpdateRequest.getImgUrl();
  }
}
