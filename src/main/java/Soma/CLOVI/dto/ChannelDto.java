package Soma.CLOVI.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ChannelDto {
    @NotNull
    @Size(min = 1, max = 255)
    private String channelUrl;

    @NotNull
    private String channelName;

    @NotNull
    private String profileUrl;
}
