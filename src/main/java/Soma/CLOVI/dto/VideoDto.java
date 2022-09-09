package Soma.CLOVI.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
public class VideoDto {

    @NotNull
    @Size(max=255)
    private String videoUrl;
    @NotNull
    private String title;

    @Size(max=255)
    private String thumbnailUrl;

    @NotNull
    private Long length;
}
