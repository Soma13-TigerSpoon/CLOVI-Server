package Soma.CLOVI.repository;

import Soma.CLOVI.domain.user.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}
