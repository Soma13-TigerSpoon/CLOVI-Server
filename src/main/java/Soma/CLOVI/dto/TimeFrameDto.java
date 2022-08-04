package Soma.CLOVI.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TimeFrameDto {
    @NotNull
    private String videoUrl;
    @NotNull
    private Long start_time;
    @NotNull
    private Long end_time;
}
