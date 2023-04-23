package com.clovi.item.category;

import com.clovi.base.domain.BaseTimeEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private Integer depth;

  private Integer orders;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="parent_id")
  private Category ParentCategory;

  @OneToMany(mappedBy = "ParentCategory", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
  private List<Category> childCategories =  new ArrayList<>();

  public boolean isParent() {
    return this.depth.equals(0);
  }
}