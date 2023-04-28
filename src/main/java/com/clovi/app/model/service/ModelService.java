package com.clovi.app.model.service;

import com.clovi.app.model.repository.ModelRepository;
import com.clovi.app.model.dto.response.ModelResponse;

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

  public List<ModelResponse> searchList() {
    return modelRepository.findAll().stream().map(ModelResponse::new)
        .collect(Collectors.toList());
  }
}
