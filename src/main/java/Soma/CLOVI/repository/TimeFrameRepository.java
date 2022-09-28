package Soma.CLOVI.repository;

import Soma.CLOVI.domain.TimeFrame;
import Soma.CLOVI.domain.youtube.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TimeFrameRepository extends JpaRepository<TimeFrame,Long> {
    Optional<TimeFrame> findByVideoAndCapturePoint(Video video,Long capturePoint);
}
