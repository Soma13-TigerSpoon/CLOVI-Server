package com.clovi.api.controller.item;

import com.clovi.api.response.BaseResponse;
import com.clovi.api.response.MessageCode;
import com.clovi.api.response.ProcessStatus;
import com.clovi.dto.requests.item.ItemCreateRequest;
import com.clovi.dto.requests.item.ItemDeleteRequest;
import com.clovi.dto.requests.item.ItemUpdateRequest;
import com.clovi.dto.response.IdResponseDto;
import com.clovi.dto.requests.TimeItemRequestDto;
import com.clovi.dto.response.ItemResponseDto;
import com.clovi.service.item.ItemService;
import com.clovi.service.query.ItemQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(name = "아이템 API")
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

  @PostMapping("/items")
  public ResponseEntity saveItem(@Valid @RequestBody TimeItemRequestDto timeItemRequestDto) {
    // System.out.println(timeItemRequestDto);
    Long savedId = itemQueryService.save(timeItemRequestDto);

    return new ResponseEntity<>(
            new BaseResponse(new IdResponseDto(savedId), HttpStatus.CREATED.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_CREATE),
            HttpStatus.CREATED
    );
  }
  @PostMapping("/item")//아이템 생성 API
  @Operation(summary = "아이템 생성", description = "아이템을 직접 입력해 저장한다.")
  public ResponseEntity createItem(@Validated @RequestBody ItemCreateRequest itemCreateRequest, Long userId){
    IdResponseDto savedId = new IdResponseDto(itemService.create(itemCreateRequest,userId));
    return ResponseEntity.created(
        URI.create("/api/v1/evaluation/" + savedId.getSavedId())).body(new BaseResponse(savedId, HttpStatus.CREATED.value(),ProcessStatus.SUCCESS,
        MessageCode.SUCCESS_CREATE));
  }
  @PutMapping("/item")//아이템 수정 API
  @Operation(summary = "아이템 수정", description = "아이템을 직접 입력해 수정한다.")
  public ResponseEntity updateItem(@Validated @RequestBody ItemUpdateRequest itemUpdateRequest, Long userId){
    IdResponseDto savedId = new IdResponseDto(itemService.update(itemUpdateRequest,userId));
    return ResponseEntity.ok(new BaseResponse(savedId,HttpStatus.OK.value(),ProcessStatus.SUCCESS, MessageCode.SUCCESS_UPDATE));
  }
  @DeleteMapping("/item")//아이템 삭제 API
  @Operation(summary = "아이템 삭제", description = "아이템을 삭제한다.")
  public ResponseEntity deleteItem(@Validated @RequestBody ItemDeleteRequest itemDeleteRequest, Long userId){
    itemService.delete(itemDeleteRequest,userId);
    return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),ProcessStatus.SUCCESS, MessageCode.SUCCESS_DELETE));
  }

}
