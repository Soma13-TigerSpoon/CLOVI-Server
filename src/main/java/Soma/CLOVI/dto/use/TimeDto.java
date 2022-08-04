package Soma.CLOVI.dto.use;

import Soma.CLOVI.domain.TimeFrame;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
public class TimeDto {
    @NotNull
    private Long start_time;
    @NotNull
    private Long end_time;

    public void createTimeDto(TimeFrame timeFrame){
        start_time = timeFrame.getStartTime();
        end_time = timeFrame.getEndTime();
    }
}
