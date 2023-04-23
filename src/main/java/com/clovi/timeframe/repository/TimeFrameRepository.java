package com.clovi.timeframe.repository;

import com.clovi.timeframe.TimeFrame;
import com.clovi.video.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TimeFrameRepository extends JpaRepository<TimeFrame,Long> {
    Optional<TimeFrame> findByVideoAndCapturePoint(Video video,Long capturePoint);
    List<TimeFrame> findAllByVideoIdAndDeletedIsFalse(Long videoId);
    Optional<TimeFrame> findByIdAndDeletedIsFalse(Long id);

    boolean existsByVideoIdAndCapturePointAndDeletedIsFalse(Long videoId, Long capturePoint);
 }
