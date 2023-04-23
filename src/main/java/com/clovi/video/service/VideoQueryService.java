package com.clovi.video.service;

import com.clovi.channel.Channel;
import com.clovi.video.dto.request.VideoRequestDto;
import com.clovi.channel.ChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class VideoQueryService {

  private final VideoService videoService;
  private final ChannelService channelService;
  @Transactional
  public Long save(VideoRequestDto videoRequestDto) {
    Channel channel = channelService.findChannelByNameAndSave(videoRequestDto.getChannelName(), videoRequestDto.getChannelUrl());
    Long savedId = videoService.save(videoRequestDto,channel);
    return savedId;
  }
}
