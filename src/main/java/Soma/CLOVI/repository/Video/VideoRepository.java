package Soma.CLOVI.repository.Video;

import Soma.CLOVI.domain.youtube.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video,Long> {
    Optional<Video> findByVideoUrl(String videoUrl);
}
