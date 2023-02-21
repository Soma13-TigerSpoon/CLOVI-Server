package com.clovi.dto.response;

import lombok.Getter;

@Getter
public class IdResponseDto {

  private Long savedId;

  public IdResponseDto(Long savedId) {
    this.savedId = savedId;
  }
}
