package com.clovi.app.timeframe.dto.response;

import com.clovi.app.timeframe.domain.Timeframe;
import lombok.Getter;


@Getter
public class TimeframeResponse {

  private Long id;
  private Long start;

  public TimeframeResponse(Timeframe timeFrame) {
    this.id = timeFrame.getId();
    this.start = timeFrame.getCapturePoint();
  }
}
