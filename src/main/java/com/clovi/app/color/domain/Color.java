package com.clovi.app.color.domain;

import com.clovi.app.base.domain.BaseTimeEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.Unique;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Color extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;
  @Unique
  private String name;

  public Color(String name) {
    this.name = name;
  }
}
