package com.clovi.app.timeframe.repository;

import com.clovi.app.timeframe.domain.Timeframe;
import com.clovi.app.video.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TimeframeRepository extends JpaRepository<Timeframe,Long> {
    Optional<Timeframe> findByVideoAndCapturePoint(Video video, Long capturePoint);
}
