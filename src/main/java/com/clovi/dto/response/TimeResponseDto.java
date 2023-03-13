package com.clovi.dto.response;

import com.clovi.domain.TimeFrame;
import lombok.Getter;


@Getter
public class TimeResponseDto {

  private Long id;
  private Long start;

  public TimeResponseDto(TimeFrame timeFrame) {
    this.id = timeFrame.getId();
    this.start = timeFrame.getCapturePoint();
  }
}
