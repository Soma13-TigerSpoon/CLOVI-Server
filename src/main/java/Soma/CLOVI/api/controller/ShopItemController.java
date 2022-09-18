package Soma.CLOVI.api.controller;

import Soma.CLOVI.api.response.BaseResponse;
import Soma.CLOVI.api.response.MessageCode;
import Soma.CLOVI.api.response.ProcessStatus;
import Soma.CLOVI.dto.requests.ShopItemRequestDto;
import Soma.CLOVI.dto.use.IdResponseDto;
import Soma.CLOVI.service.ShopItemService;
import com.fasterxml.jackson.databind.ser.Serializers.Base;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShopItemController {

  private final ShopItemService shopItemService;

  @PostMapping("/api/v1/shopItems")
  public ResponseEntity saveShopItemV1(@RequestBody ShopItemRequestDto shopItemRequestDto) {
    return new ResponseEntity<>(
        new BaseResponse(new IdResponseDto(shopItemService.save(shopItemRequestDto)),
            HttpStatus.CREATED.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_GET),
        HttpStatus.CREATED);
  }
}
