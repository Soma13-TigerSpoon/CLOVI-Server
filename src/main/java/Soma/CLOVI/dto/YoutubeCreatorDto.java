package Soma.CLOVI.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class YoutubeCreatorDto {

    @NotNull
    private String creatorName;

    @NotNull
    @Size(max = 255)
    private String profileImgUrl;
}
