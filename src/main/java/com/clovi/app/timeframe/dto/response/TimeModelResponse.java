package com.clovi.app.timeframe.dto.response;

import com.clovi.app.timeframe.Timeframe;
import com.clovi.app.model.dto.response.ModelResponse;
import lombok.Getter;

@Getter
public class TimeModelResponse {
    private final TimeframeResponse time;
    private final ModelResponse model;

    public TimeModelResponse(Timeframe timeFrame) {
        this.time = new TimeframeResponse(timeFrame);
        this.model = new ModelResponse(timeFrame.getModel());
    }
}
