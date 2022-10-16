package Soma.CLOVI.api.controller;

import Soma.CLOVI.api.response.BaseResponse;
import Soma.CLOVI.api.response.MessageCode;
import Soma.CLOVI.api.response.ProcessStatus;
import Soma.CLOVI.dto.response.IdResponseDto;
import Soma.CLOVI.dto.requests.TimeItemRequestDto;
import Soma.CLOVI.service.ItemService;
import Soma.CLOVI.service.query.ItemQueryService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemController {

  private final ItemService itemService;

  private final ItemQueryService itemQueryService;

  @GetMapping("/api/v1/items")
  public ResponseEntity getItemsV1(@RequestParam("itemIdList") List<Long> itemIdList) {
    return ResponseEntity.ok(new BaseResponse(itemService.getItems(itemIdList), HttpStatus.OK.value(), ProcessStatus.SUCCESS,
        MessageCode.SUCCESS_GET));
  }

  @PostMapping("/api/v1/items")
  public ResponseEntity saveItemV1(@Valid @RequestBody TimeItemRequestDto timeItemRequestDto) {
    System.out.println(timeItemRequestDto);
    return new ResponseEntity<>(new BaseResponse(new IdResponseDto(itemQueryService.save(timeItemRequestDto)), HttpStatus.CREATED.value(),
        ProcessStatus.SUCCESS, MessageCode.SUCCESS_CREATE),HttpStatus.CREATED);
  }
}
