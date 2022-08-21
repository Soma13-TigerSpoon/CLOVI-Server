package Soma.CLOVI.service;

import Soma.CLOVI.domain.Model;
import Soma.CLOVI.dto.use.ModelResponseDto;
import Soma.CLOVI.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModelService {
    private final ModelRepository modelRepository;
    public List<ModelResponseDto> searchList() {
        return modelRepository.findAll().stream().map(ModelResponseDto::new).collect(Collectors.toList());
    }
}
