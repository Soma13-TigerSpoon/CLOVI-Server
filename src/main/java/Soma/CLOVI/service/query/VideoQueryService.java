package Soma.CLOVI.service.query;

import Soma.CLOVI.domain.youtube.Channel;
import Soma.CLOVI.domain.youtube.Video;
import Soma.CLOVI.dto.requests.VideoRequestDto;
import Soma.CLOVI.repository.Video.VideoRepository;
import Soma.CLOVI.service.ChannelService;
import Soma.CLOVI.service.VideoService;
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
