package Soma.CLOVI.repository;

import Soma.CLOVI.domain.TimeFrame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TimeFrameRepository extends JpaRepository<TimeFrame,Long> {
    Optional<TimeFrame> findByCapturePointBetween(Long capturePoint1, Long capturePoint2);
}
