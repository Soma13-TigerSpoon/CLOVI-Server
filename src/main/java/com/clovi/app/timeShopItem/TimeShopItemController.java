package com.clovi.app.timeShopItem;

import com.clovi.app.base.dto.response.BaseResponse;
import com.clovi.app.base.dto.response.MessageCode;
import com.clovi.app.base.dto.response.ProcessStatus;
import com.clovi.app.base.dto.response.SavedId;
import com.clovi.app.auth.helper.AuthMember;
import com.clovi.app.member.Member;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@Tag(name = "[TimeShopItem] 시간에 대한 아이템 및 쇼핑몰 링크 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TimeShopItemController {
    private final TimeShopItemService timeShopItemService;

    // 시간에 대한 item 및 shopItem 연관관계 생성 API
    @PostMapping("/timeframes/{timeframe_id}")
    @Operation(summary = "Create relationship between timeframe and item and shopItem", description = "Create a timeShopItem and save.", responses = {
            @ApiResponse(responseCode = "201", description = "Success create timeShopItem!", content = @Content(schema = @Schema(implementation = SavedId.class)))
    })
    public ResponseEntity createTimeShopItem(@Validated @PathVariable(name = "timeframe_id") Long timeframeId,
                                             @Validated @RequestParam(name = "item_id") Long itemId, @Validated @RequestParam(name = "shop_item_id") Long shopItemId,
                                             @AuthMember Member member) {
        SavedId savedId = new SavedId(timeShopItemService.createTimeShopItem(timeframeId, itemId, shopItemId, member));

        String[] list = {"/api/v1/timeframes", String.valueOf(timeframeId)};
        return ResponseEntity.created(
                URI.create(String.join("/", list))
        ).body(
                new BaseResponse(savedId, HttpStatus.CREATED.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_CREATE)
        );
    }

    // 시간에 대한 item 및 shopItem 연관관계 삭제 API
    @DeleteMapping("/timeframes/{timeframe_id}")
    @Operation(summary = "Delete relationship between timeframe and item and shopItem", description = "Delete a timeShopItem.", responses = {
            @ApiResponse(responseCode = "200", description = "Success delete timeShopItem!")
    })
    public ResponseEntity deleteTimeShopItem(@Validated @PathVariable(name = "timeframe_id") Long timeframeId,
                                             @Validated @RequestParam(name = "item_id") Long itemId, @Validated @RequestParam(name = "shop_item_id") Long shopItemId,
                                             @AuthMember Member member) {
        timeShopItemService.deleteTimeShopItem(timeframeId, itemId, shopItemId, member);
        return ResponseEntity.ok(
                new BaseResponse(HttpStatus.OK.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_DELETE)
        );
    }
}