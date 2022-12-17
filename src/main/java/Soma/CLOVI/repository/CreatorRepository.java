package Soma.CLOVI.repository;

import Soma.CLOVI.domain.user.Creator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YoutubeCreatorRepository extends JpaRepository<Creator, Long> {
}
