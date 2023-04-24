package com.clovi.channel.dto.response;

import com.clovi.channel.Channel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(name = "채널 정보 응답")
public class ChannelResponseDto {

  private Long id;
  private String name;
  private String profileUrl;
  private String channelId;

  public ChannelResponseDto(Channel channel) {
    this.id = channel.getId();
    this.name = channel.getName();
    this.profileUrl = channel.getProfileImgUrl();
    this.channelId = channel.getChannelId();
  }
}
