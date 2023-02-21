package com.clovi.repository;

import com.clovi.domain.TimeFrame;
import com.clovi.domain.youtube.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TimeFrameRepository extends JpaRepository<TimeFrame,Long> {
    Optional<TimeFrame> findByVideoAndCapturePoint(Video video,Long capturePoint);
}
