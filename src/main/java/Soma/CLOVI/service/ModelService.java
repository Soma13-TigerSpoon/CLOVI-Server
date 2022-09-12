package Soma.CLOVI.service;

import Soma.CLOVI.dto.use.ModelResponseDto;
import Soma.CLOVI.repository.ModelRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ModelService {

  private final ModelRepository modelRepository;

  public List<ModelResponseDto> searchList() {
    return modelRepository.findAll().stream().map(ModelResponseDto::new)
        .collect(Collectors.toList());
  }
}
