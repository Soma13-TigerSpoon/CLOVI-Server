package Soma.CLOVI.service;

import Soma.CLOVI.domain.youtube.Channel;
import Soma.CLOVI.repository.ChannelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChannelService {

  private final ChannelRepository channelRepository;

  @Transactional
  public Channel findChannelByNameAndSave(String channelName, String channelUrl){
    Channel channel = channelRepository.findByName(channelName).orElse(
        new Channel(channelName,channelUrl,null,null) // 지금 유튜버는 채널명만 이름으로 가지고 있도록 함.
    );
    channelRepository.save(channel);
    return channel;
  }
}
