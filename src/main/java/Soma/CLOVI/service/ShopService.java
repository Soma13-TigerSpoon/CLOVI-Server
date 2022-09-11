package Soma.CLOVI.service;

import Soma.CLOVI.dto.use.ModelResponseDto;
import Soma.CLOVI.dto.use.ShopResponseDto;
import Soma.CLOVI.repository.ModelRepository;
import Soma.CLOVI.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ShopService {
    private final ShopRepository shopRepository;
    public List<ShopResponseDto> getShops() {
        return shopRepository.findAll().stream().map(ShopResponseDto::new).collect(Collectors.toList());
    }
}
