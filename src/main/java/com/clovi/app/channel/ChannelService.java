package com.clovi.app.channel;

import com.clovi.app.channel.dto.response.ChannelResponse;
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
        new Channel(channelName, channelUrl, null)
    );

    channelRepository.save(channel);
    return channel;
  }

  public List<ChannelResponse> findAllChannels() {
    List<Channel> channelList = channelRepository.findAll();
    return channelList.stream().map(ChannelResponse::new).collect(Collectors.toList());
  }
}
