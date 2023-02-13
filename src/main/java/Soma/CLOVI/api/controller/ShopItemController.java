package Soma.CLOVI.api.controller;

import Soma.CLOVI.api.response.BaseResponse;
import Soma.CLOVI.api.response.MessageCode;
import Soma.CLOVI.api.response.ProcessStatus;
import Soma.CLOVI.dto.requests.ItemCreateRequest;
import Soma.CLOVI.dto.requests.ItemDeleteRequest;
import Soma.CLOVI.dto.requests.ItemUpdateRequest;
import Soma.CLOVI.dto.requests.ShopItemCreateRequest;
import Soma.CLOVI.dto.requests.ShopItemDeleteRequest;
import Soma.CLOVI.dto.requests.ShopItemRequestDto;
import Soma.CLOVI.dto.response.IdResponseDto;
import Soma.CLOVI.service.item.ShopItemService;
import io.swagger.v3.oas.annotations.Operation;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShopItemController {

  private final ShopItemService shopItemService;

  @PostMapping("/item")//아이템 생성 API
  @Operation(summary = "아이템 생성", description = "아이템을 직접 입력해 저장한다.")
  public ResponseEntity createItem(@Validated @RequestBody ShopItemCreateRequest shopItemCreateRequest, Long userId){
    IdResponseDto savedId = new IdResponseDto(shopItemService.create(shopItemCreateRequest,userId));
    return ResponseEntity.created(
        URI.create("/api/v1/evaluation/" + savedId.getSavedId())).body(new BaseResponse(savedId, HttpStatus.CREATED.value(),ProcessStatus.SUCCESS,
        MessageCode.SUCCESS_CREATE));
  }
  @DeleteMapping("/item")//아이템 삭제 API
  @Operation(summary = "아이템 삭제", description = "아이템을 삭제한다.")
  public ResponseEntity deleteItem(@Validated @RequestBody ShopItemDeleteRequest shopItemDeleteRequest, Long userId){
    shopItemService.delete(shopItemDeleteRequest,userId);
    return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),ProcessStatus.SUCCESS, MessageCode.SUCCESS_DELETE));
  }

}
