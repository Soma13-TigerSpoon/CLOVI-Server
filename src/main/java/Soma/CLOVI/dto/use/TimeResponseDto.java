package Soma.CLOVI.dto.use;

import Soma.CLOVI.domain.TimeFrame;
import lombok.Getter;



@Getter
public class TimeResponseDto {
    private Long start;
    private Long end;

    public TimeResponseDto(TimeFrame timeFrame){
        start = timeFrame.getStartTime();
        end = timeFrame.getEndTime();
    }
}
