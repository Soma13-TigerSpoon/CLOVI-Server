package com.clovi.app.video.dto.request;

import lombok.Data;

@Data
public class VideoRequest {

  private String channelName;
  private String channelUrl;
  private String youtubeVideoId;
  private String videoTitle;
  private Long videoLength;
  private String uploadDate;
}
