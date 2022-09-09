package Soma.CLOVI.api.controller;

import Soma.CLOVI.api.response.BaseResponse;
import Soma.CLOVI.api.response.MessageCode;
import Soma.CLOVI.api.response.ProcessStatus;
import Soma.CLOVI.dto.use.TimeItemRequestDto;
import Soma.CLOVI.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/api/v1/items")
    public BaseResponse getItemsV1(@RequestParam("itemIdList") List<Long> itemIdList){
        return new BaseResponse(itemService.getItems(itemIdList), HttpStatus.OK, ProcessStatus.SUCCESS, MessageCode.SUCCESS_GET);
    }

    @PostMapping("/api/v1/items")
    public BaseResponse saveItemV1(@RequestBody TimeItemRequestDto timeItemRequestDto){
        return new BaseResponse(itemService.save(timeItemRequestDto), HttpStatus.OK, ProcessStatus.SUCCESS, MessageCode.SUCCESS_CREATE);
    }
}
