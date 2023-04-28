package com.clovi.app.channel.dto.response;

import com.clovi.app.channel.domain.Channel;
import lombok.Getter;

@Getter
public class ChannelResponse {

  private Long Id;
  private String Name;
  private String ProfileUrl;
  private String Url;

  public ChannelResponse(Channel channel) {
    this.Id = channel.getId();
    this.Name = channel.getName();
    this.ProfileUrl = channel.getProfileImgUrl();
    this.Url = channel.getChannelUrl();
  }
}
