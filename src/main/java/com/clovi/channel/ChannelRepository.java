package com.clovi.channel;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
    Optional<Channel> findById(Long channelId);
    Optional<Channel> findByName(String name);
    Optional<Channel> findByChannelIdAndDeletedFalse(String channelId);

    List<Channel> findAll();
}
