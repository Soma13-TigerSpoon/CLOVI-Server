package com.clovi.app.model.controller;

import com.clovi.app.base.dto.response.BaseResponse;
import com.clovi.app.base.dto.response.MessageCode;
import com.clovi.app.base.dto.response.ProcessStatus;
import com.clovi.app.model.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ModelController {
    private final ModelService modelService;

    @GetMapping("/api/v1/models")
    public ResponseEntity getModelV1() {
        return ResponseEntity.ok(new BaseResponse(modelService.searchList(), HttpStatus.OK.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_GET_LIST));
    }
}
