package com.clovi.app.timeframe;

import com.clovi.app.base.dto.response.BaseResponse;
import com.clovi.app.base.dto.response.MessageCode;
import com.clovi.app.base.dto.response.ProcessStatus;
import com.clovi.app.base.dto.response.SavedId;
import com.clovi.app.timeShopItem.dto.response.TimeShopItemResponse;
import com.clovi.app.timeframe.dto.request.TimeframeCreateRequest;
import com.clovi.app.timeframe.dto.request.TimeframeUpdateRequest;
import com.clovi.app.timeframe.dto.response.TimeframeResponse;
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
import java.util.List;

@Tag(name = "[Timeframe] 아이템 등장 시간 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TimeframeController {
    private final TimeframeService timeFrameService;

    // 영상에 대한 모든 시간 조회
    @GetMapping("/videos/{video_id}/timeframes")
    @Operation(summary = "Find all timeframes", description = "Find all timeframes by video ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Success find timeframe list!", content = @Content(schema = @Schema(implementation = TimeframeResponse.class)))
    })
    public ResponseEntity findAllTimeframesByVideoId(@PathVariable(name = "video_id") String videoId) {
        List<TimeframeResponse> response = timeFrameService.getTimeframeListByVideoId(videoId);
        return ResponseEntity.ok(new BaseResponse(response, HttpStatus.OK.value(), MessageCode.SUCCESS_GET_LIST));
    }

    // 시간으로 해당 시간에 있는 아이템 리스트 조회 API
    @GetMapping("/videos/{video_id}/timeframes/{timeframe_id}")
    @Operation(summary = "Find a specific timeframe", description = "Find a timeframe by video ID and timeframe ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Success find timeShopItem list!", content = @Content(schema = @Schema(implementation = TimeShopItemResponse.class)))
    })
    public ResponseEntity findItemsByVideoIdAndTimeframeId(@PathVariable(name = "video_id") String videoId, @PathVariable(name = "timeframe_id") Long timeframeId) {
        TimeShopItemResponse response = timeFrameService.getItemListByVideoIdAndTimeframeId(videoId, timeframeId);
        return ResponseEntity.ok(new BaseResponse(response, HttpStatus.OK.value(), MessageCode.SUCCESS_GET));
    }

    // 시간 생성 API
    @PostMapping("/videos/{video_id}/timeframes")
    @Operation(summary = "Create a specific timeframe", description = "Create timeframe and save.", responses = {
            @ApiResponse(responseCode = "201", description = "Success create timeframe!", content = @Content(schema = @Schema(implementation = SavedId.class)))
    })
    public ResponseEntity createTimeframe(@PathVariable(name = "video_id") String videoId,
                                          @Validated @RequestBody TimeframeCreateRequest timeframeCreateRequest, @AuthMember Member member) {
        SavedId savedId = new SavedId(
                timeFrameService.createTimeframe(timeframeCreateRequest, videoId, member)
        );

        String[] list = {"/api/v1/videos", videoId, "timeframes", String.valueOf(savedId.getId())};

        return ResponseEntity.created(
                URI.create(String.join("/", list))
        ).body(
                new BaseResponse(savedId, HttpStatus.CREATED.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_CREATE)
        );
    }

    // 시간 수정 API
    @PutMapping("/videos/{video_id}/timeframes/{timeframe_id}")
    @Operation(summary = "Update a specific timeframe", description = "Update a timeframe by video ID and timeframe ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Success update timeframe!", content = @Content(schema = @Schema(implementation = SavedId.class)))
    })
    public ResponseEntity updateTimeframe(@PathVariable(name = "video_id") String videoId, @PathVariable(name = "timeframe_id") Long timeframeId,
                                          @Validated @RequestBody TimeframeUpdateRequest timeframeUpdateRequest, @AuthMember Member member) {
        SavedId savedId = new SavedId(
                timeFrameService.updateTimeframe(timeframeUpdateRequest, videoId, timeframeId, member)
        );

        return ResponseEntity.ok(
                new BaseResponse(savedId, HttpStatus.OK.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_UPDATE)
        );
    }

    // 시간 삭제 API
    @DeleteMapping("/videos/{video_id}/timeframes/{timeframe_id}")
    @Operation(summary = "Delete a specific timeframe", description = "Delete a timeframe by video ID and timeframe ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Success delete timeframe!")
    })
    public ResponseEntity deleteTimeframe(@PathVariable(name = "video_id") String videoId, @PathVariable(name = "timeframe_id") Long timeframeId,
                                          @AuthMember Member member) {
        timeFrameService.deleteTimeframe(videoId, timeframeId, member);

        return ResponseEntity.ok(
                new BaseResponse(HttpStatus.OK.value(),ProcessStatus.SUCCESS, MessageCode.SUCCESS_DELETE)
        );
    }
}
