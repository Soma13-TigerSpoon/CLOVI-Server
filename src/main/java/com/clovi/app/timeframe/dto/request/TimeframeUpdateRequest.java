package com.clovi.app.timeframe.dto.request;

import com.clovi.app.timeframe.Timeframe;
import com.clovi.utils.TimeFormatUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Schema(name = "시간 수정 요청")
@NoArgsConstructor
public class TimeframeUpdateRequest {
    @NotBlank
    @Schema(description = "시간 형식 -> 1:02:23 or 2:24 or 04:25 or 35...", example = "01:00")
    @Pattern(regexp = "(2[0-3]|[01]\\d:)?([0-5]?\\d:)?([0-5]?\\d)+")
    private String time;

    public TimeframeUpdateRequest(String time) {
        this.time = time;
    }

    public Long getTime() {
        return TimeFormatUtils.StringTimeToLong(this.time);
    }

    public Timeframe update(Timeframe timeframe, Long id) {
        timeframe.setCapturePoint(Long.parseLong(this.time));
        timeframe.setLastModifiedBy(id);

        return timeframe;
    }
}
