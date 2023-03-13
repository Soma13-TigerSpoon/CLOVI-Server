package com.clovi.dto.response;

import com.clovi.domain.youtube.Channel;
import lombok.Getter;

@Getter
public class ChannelResponseDto {

  private Long Id;
  private String Name;
  private String ProfileUrl;
  private String Url;

  public ChannelResponseDto(Channel channel) {
    this.Id = channel.getId();
    this.Name = channel.getName();
    this.ProfileUrl = channel.getProfileImgUrl();
    this.Url = channel.getChannelUrl();
  }
}
