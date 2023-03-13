package com.clovi.service.query;

import com.clovi.domain.youtube.Channel;
import com.clovi.dto.requests.VideoRequestDto;
import com.clovi.service.ChannelService;
import com.clovi.service.VideoService;
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
