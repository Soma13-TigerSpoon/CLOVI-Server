package Soma.CLOVI.api.controller;

import Soma.CLOVI.api.response.BaseResponse;
import Soma.CLOVI.api.response.MessageCode;
import Soma.CLOVI.api.response.ProcessStatus;
import Soma.CLOVI.domain.Model;
import Soma.CLOVI.dto.use.VideoResponseDto;
import Soma.CLOVI.repository.ModelRepository;
import Soma.CLOVI.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ModelController {
    private final ModelService modelService;

    @GetMapping("/api/v1/models")
    public ResponseEntity getModelV1() {
        return ResponseEntity.ok(new BaseResponse(modelService.searchList(), HttpStatus.OK.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_GET_LIST));
    }
}
