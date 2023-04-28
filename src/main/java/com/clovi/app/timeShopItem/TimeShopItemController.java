package com.clovi.app.timeShopItem;

import com.clovi.app.base.dto.response.BaseResponse;
import com.clovi.app.base.dto.response.MessageCode;
import com.clovi.app.base.dto.response.ProcessStatus;
import com.clovi.app.base.dto.response.SavedId;
import com.clovi.app.auth.support.AuthMember;
import com.clovi.app.member.Member;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.net.URI;


@Tag(name = "[TimeShopItem] 시간에 대한 아이템 및 쇼핑몰링크 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TimeShopItemController {
    private final TimeShopItemService timeShopItemService;

    // 시간에 대한 item, shopitem 생성 API
    @PostMapping("/videos/{video_id}/timeframes/{time_frame_id}/items/{item_id}/shopitems/{shop_item_id}")
    @Operation(summary = "Create timeShopItem Relationship", description = "Create timeShopItem and save", responses = {
            @ApiResponse(responseCode = "201", description = "Success create", content = @Content(schema = @Schema(implementation = SavedId.class)))
    })
    public ResponseEntity createTimeShopItem(@NotBlank @PathVariable(name = "video_id") Long videoId, @AuthMember Member member, @NotBlank @PathVariable(name = "time_frame_id") Long timeFrameId
            , @PathVariable(name = "item_id") Long itemId, @PathVariable(name = "shop_item_id") Long shopItemId){
        SavedId savedId = new SavedId(timeShopItemService.create(member,timeFrameId,itemId,shopItemId));
        String[] list = {"/api/v1/videos", String.valueOf(videoId), "timeframes", String.valueOf(timeFrameId)};
        return ResponseEntity.created(
                URI.create(String.join("/", list))).body(new BaseResponse(savedId, HttpStatus.CREATED.value(),
                ProcessStatus.SUCCESS,
                MessageCode.SUCCESS_CREATE));
    }
}