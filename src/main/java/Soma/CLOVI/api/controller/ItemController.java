package Soma.CLOVI.api.controller;

import Soma.CLOVI.api.response.BaseResponse;
import Soma.CLOVI.api.response.MessageCode;
import Soma.CLOVI.api.response.ProcessStatus;
import Soma.CLOVI.dto.response.IdResponseDto;
import Soma.CLOVI.dto.requests.TimeItemRequestDto;
import Soma.CLOVI.dto.response.ItemResponseDto;
import Soma.CLOVI.service.ItemService;
import Soma.CLOVI.service.query.ItemQueryService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ItemController {
  private final ItemService itemService;
  private final ItemQueryService itemQueryService;

  @GetMapping("/item/{item_id}")
  public ResponseEntity getOneItem(@Validated @PathVariable(name = "item_id") Long itemId) {
    ItemResponseDto result = itemService.getItemById(itemId);

    if(result == null) {
      return ResponseEntity.badRequest().body(
              new BaseResponse(HttpStatus.BAD_REQUEST.value(), ProcessStatus.FAIL, MessageCode.ERROR_REQ_PARAM_ITEM_ID)
      );
    }

    return ResponseEntity.ok(
            new BaseResponse(result, HttpStatus.OK.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_GET)
    );
  }

  @GetMapping("/items")
  public ResponseEntity getMultipleItems(@RequestParam("itemIdList") List<Long> itemIdList) {
    List<ItemResponseDto> result = itemService.getItemsByIdList(itemIdList);

    return ResponseEntity.ok(
            new BaseResponse(result, HttpStatus.OK.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_GET)
    );
  }

  @PostMapping("/items")
  public ResponseEntity saveItem(@Valid @RequestBody TimeItemRequestDto timeItemRequestDto) {
    // System.out.println(timeItemRequestDto);
    Long savedId = itemQueryService.save(timeItemRequestDto);

    return new ResponseEntity<>(
            new BaseResponse(new IdResponseDto(savedId), HttpStatus.CREATED.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_CREATE),
            HttpStatus.CREATED
    );
  }
}
