package com.clovi.app.video.repository;

import com.clovi.app.video.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video,Long> {
    Optional<Video> findByYoutubeVideoId(String youtubeVideoId);
    Optional<Video> findByIdAndDeletedIsFalse(Long id);

    Optional<Video> findByYoutubeVideoIdAndDeletedIsFalse(String youtubeVideoId);
}
