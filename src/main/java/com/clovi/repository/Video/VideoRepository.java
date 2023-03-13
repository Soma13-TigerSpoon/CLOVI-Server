package com.clovi.repository.Video;

import com.clovi.domain.youtube.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video,Long> {
    Optional<Video> findByYoutubeVideoId(String youtubeVideoId);
}
