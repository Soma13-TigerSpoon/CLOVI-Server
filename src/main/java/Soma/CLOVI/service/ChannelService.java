package Soma.CLOVI.service;

import Soma.CLOVI.domain.youtube.Channel;
import Soma.CLOVI.dto.response.ChannelResponseDto;
import Soma.CLOVI.repository.ChannelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChannelService {
  private final ChannelRepository channelRepository;

  @Transactional
  public Channel findChannelByNameAndSave(String channelName, String channelUrl){
    Channel channel = channelRepository.findByName(channelName).orElse(
        new Channel(channelName, channelUrl, null, null)
    );

    channelRepository.save(channel);
    return channel;
  }

  public List<ChannelResponseDto> findAllChannels() {
    List<Channel> channelList = channelRepository.findAll();
    return channelList.stream().map(ChannelResponseDto::new).collect(Collectors.toList());
  }
}
