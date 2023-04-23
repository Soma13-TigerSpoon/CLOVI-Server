package com.clovi.timeframe.dto.response;

import com.clovi.timeframe.TimeFrame;
import lombok.Getter;


@Getter
public class TimeFrameResponseDto {

  private Long id;
  private Long start;

  public TimeFrameResponseDto(TimeFrame timeFrame) {
    this.id = timeFrame.getId();
    this.start = timeFrame.getCapturePoint();
  }
}
