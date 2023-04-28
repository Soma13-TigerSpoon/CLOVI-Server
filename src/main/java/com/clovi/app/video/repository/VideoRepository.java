package com.clovi.app.video.repository;

import com.clovi.app.video.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video,Long> {
    Optional<Video> findByYoutubeVideoId(String youtubeVideoId);
}
