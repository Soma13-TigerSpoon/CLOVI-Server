package Soma.CLOVI.dto.requests;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

@Data
public class VideoRequestDto {

  private String channelName;
  private String channelUrl;
  private String youtubeVideoId;
  private String videoTitle;
  private Long videoLength;
  private String uploadDate;
}
