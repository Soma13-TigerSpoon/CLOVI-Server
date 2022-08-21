package Soma.CLOVI.repository;

import Soma.CLOVI.domain.youtube.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChannelRepository extends JpaRepository<Channel,Long> {
    Optional<Channel> findByName(String name);
}
