package com.clovi.service;

import com.clovi.domain.shop.Shop;
import com.clovi.dto.response.ShopResponseDto;
import com.clovi.repository.ShopRepository;
import java.util.List;
import java.util.Optional;
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
  @Transactional
  public Shop getByHostname(String hostname) {
    Optional<Shop> shop =  shopRepository.findByHostname(hostname);
    Shop result;
    if(shop.isEmpty()){
      result = new Shop(hostname);
      shopRepository.save(result);
    }
    else{
      result = shop.get();
    }
    return result;
  }
}
