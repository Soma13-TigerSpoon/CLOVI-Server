package Soma.CLOVI.dto.requests;

import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

@Data
public class VideoRequestDto {

  private String channelName;
  private String channelUrl;
  private String videoUrlId;
  private String videoTitle;
  private Long videoLength;
}
