package Soma.CLOVI.repository;

import Soma.CLOVI.domain.youtube.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel,Long> {
}
