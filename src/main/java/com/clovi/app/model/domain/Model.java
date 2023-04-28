package com.clovi.app.model.domain;

import com.clovi.app.base.domain.BaseTimeEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Model extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private float height_cm;
  private float weight_kg;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  public Model(String name, float height_cm, float weight_kg, Gender gender) {
    this.name = name;
    this.height_cm = height_cm;
    this.weight_kg = weight_kg;
    this.gender = gender;
  }
}
