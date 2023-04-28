package com.clovi.app.video.service;

import com.clovi.app.channel.domain.Channel;
import com.clovi.app.video.dto.request.VideoRequest;
import com.clovi.app.channel.service.ChannelService;
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
  public Long save(VideoRequest videoRequest) {
    Channel channel = channelService.findChannelByNameAndSave(videoRequest.getChannelName(), videoRequest.getChannelUrl());
    Long savedId = videoService.save(videoRequest,channel);
    return savedId;
  }
}
