package com.clovi.app.shop;

import com.clovi.app.shop.dto.response.ShopResponse;

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

  public List<ShopResponse> getShops() {
    return shopRepository.findAll().stream().map(ShopResponse::new).collect(Collectors.toList());
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
