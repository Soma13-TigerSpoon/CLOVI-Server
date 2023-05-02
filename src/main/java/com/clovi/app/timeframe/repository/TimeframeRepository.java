package com.clovi.app.timeframe.repository;

import com.clovi.app.timeframe.Timeframe;
import com.clovi.app.video.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TimeframeRepository extends JpaRepository<Timeframe,Long> {
    Optional<Timeframe> findByVideoAndCapturePoint(Video video, Long capturePoint);
    List<Timeframe> findAllByVideoIdAndDeletedIsFalse(Long videoId);
    Optional<Timeframe> findByIdAndDeletedIsFalse(Long id);
    Optional<Timeframe> findByIdAndVideoIdAndDeletedIsFalse(Long id, Long videoId);

    boolean existsByVideoIdAndCapturePointAndDeletedIsFalse(Long videoId, Long capturePoint);
 }
