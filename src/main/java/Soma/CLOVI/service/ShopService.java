package Soma.CLOVI.service;

import Soma.CLOVI.domain.shop.Shop;
import Soma.CLOVI.dto.use.ShopResponseDto;
import Soma.CLOVI.repository.ShopRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShopService {

  private final ShopRepository shopRepository;

  public List<ShopResponseDto> getShops() {
    return shopRepository.findAll().stream().map(ShopResponseDto::new).collect(Collectors.toList());
  }

  public Shop getById(Long shopId) {
    return shopRepository.findById(shopId).orElseThrow(
        () -> new IllegalArgumentException("해당 Id를 가진 쇼팡몰이 없습니다. id=" + shopId));
  }

  public Shop getByName(String shopName) {
    return shopRepository.findByName(shopName).orElse(
        new Shop(shopName, null)
    );
  }
}
