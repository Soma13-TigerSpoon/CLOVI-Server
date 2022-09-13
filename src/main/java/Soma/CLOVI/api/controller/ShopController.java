package Soma.CLOVI.api.controller;

import Soma.CLOVI.api.response.BaseResponse;
import Soma.CLOVI.api.response.MessageCode;
import Soma.CLOVI.api.response.ProcessStatus;
import Soma.CLOVI.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;

    @GetMapping("/api/v1/shops")
    public ResponseEntity getShopsV1(){
        return ResponseEntity.ok(new BaseResponse(shopService.getShops(), HttpStatus.OK.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_GET));
    }

}
