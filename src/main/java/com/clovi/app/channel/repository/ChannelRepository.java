package com.clovi.app.channel.repository;

import com.clovi.app.channel.domain.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
    Optional<Channel> findById(Long channelId);
    Optional<Channel> findByName(String name);
    Optional<Channel> findByChannelUrl(String channelUrl);

    List<Channel> findAll();
}
