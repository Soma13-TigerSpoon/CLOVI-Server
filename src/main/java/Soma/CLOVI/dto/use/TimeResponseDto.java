package Soma.CLOVI.dto.use;

import Soma.CLOVI.domain.TimeFrame;
import lombok.Getter;



@Getter
public class TimeResponseDto {
    private Long id;
    private Long start;

    public TimeResponseDto(TimeFrame timeFrame){
        this.id = timeFrame.getId();
        this.start = timeFrame.getCapturePoint();
    }
}
