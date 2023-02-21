package com.clovi.domain.user;

import lombok.Getter;

@Getter
public enum RoleName {
  Y_CREATOR_USER("유튜브 크리에이터"), ADMIN("관리자");
  private String name;

  RoleName(String name) {
    this.name = name;
  }
}
