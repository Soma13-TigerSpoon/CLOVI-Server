package com.clovi.domain.item;

import com.clovi.domain.Base.BaseEntity;
import com.clovi.dto.requests.item.detail.ItemDetailCreateRequest;
import com.clovi.dto.requests.item.detail.ItemDetailUpdateRequest;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

//유튜버가 실제로 입력하는 컬럼
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemDetail extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  private String color;
  private String size;

  @ManyToOne(fetch = FetchType.LAZY)
  private Item item;

  public ItemDetail(ItemDetailCreateRequest request, Item item, Long userId) {
    this.color = request.getColor();
    this.size = request.getSize();
    this.item = item;
    this.createBy = userId;
    this.lastModifiedBy = userId;
  }

  public void update(ItemDetailUpdateRequest request, Long userId) {
    this.color = request.getColor();
    this.size = request.getSize();
    this.lastModifiedBy = userId;
  }

  public void delete() {
    this.isDeleted = true;
  }
}
