package com.clovi.video.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@Schema(name = "동영상 생성 요청")
@NoArgsConstructor
public class VideoRequest {

  @NotBlank
  @Schema(description = "채널 이름", example = "161cm 현킴")
  private String channelName;

  @NotBlank
  @Schema(description = "채널 ID", example = "UCD7vS0E-0ArhWAqwwi6D8MQ")
  private String channelId;

  @NotBlank
  @Schema(description = "영상 ID", example = "Cl2RC1zNTeE")
  private String youtubeVideoId;

  @NotBlank
  @Schema(description = "영상 제목", example = "여러분 기다리셨죠? (브랜즈X현킴 더비슈즈)")
  private String videoTitle;

  @Schema(description = "영상 길이", example = "324")
  private Long videoLength;

  @NotBlank
  @Schema(description = "영상 업로드 날짜", example = "2023-03-09 19:15:00")
  private String uploadDate;
}
