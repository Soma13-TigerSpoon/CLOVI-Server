package com.clovi.timeframe.dto.response;

import com.clovi.timeframe.TimeFrame;
import com.clovi.model.dto.response.ModelResponseDto;
import com.clovi.timeframe.dto.response.TimeFrameResponseDto;
import lombok.Getter;

@Getter
public class TimeModelResponseDto {
    private final TimeFrameResponseDto time;
    private final ModelResponseDto model;

    public TimeModelResponseDto(TimeFrame timeFrame) {
        this.time = new TimeFrameResponseDto(timeFrame);
        this.model = new ModelResponseDto(timeFrame.getModel());
    }
}
