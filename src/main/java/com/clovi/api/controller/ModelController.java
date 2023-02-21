package com.clovi.api.controller;

import com.clovi.api.response.BaseResponse;
import com.clovi.api.response.MessageCode;
import com.clovi.api.response.ProcessStatus;
import com.clovi.service.ModelService;
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
