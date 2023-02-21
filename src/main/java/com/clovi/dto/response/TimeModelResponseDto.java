package com.clovi.dto.response;

import com.clovi.domain.TimeFrame;
import lombok.Getter;

@Getter
public class TimeModelResponseDto {
    private final TimeResponseDto time;
    private final ModelResponseDto model;

    public TimeModelResponseDto(TimeFrame timeFrame) {
        this.time = new TimeResponseDto(timeFrame);
        this.model = new ModelResponseDto(timeFrame.getModel());
    }
}
