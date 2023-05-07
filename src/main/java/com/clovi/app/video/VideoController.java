package com.clovi.app.video;

import com.clovi.app.base.dto.response.BaseResponse;
import com.clovi.app.base.dto.response.MessageCode;
import com.clovi.app.base.dto.response.ProcessStatus;
import com.clovi.app.base.dto.response.SavedId;
import com.clovi.app.video.dto.request.VideoRequest;
import com.clovi.app.video.dto.response.VideoResponse;
import com.clovi.app.video.service.VideoService;
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

@Tag(name = "[Video] 유튜브 영상 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class VideoController {
  private final VideoService videoService;

  // 영상 정보 조회 API (video ID가 주어진 경우)
  @GetMapping("/videos/{video_id}")
  @Operation(summary = "Find a specific video", description = "Find a video by video ID.", responses = {
          @ApiResponse(responseCode = "200", description = "Success find video!", content = @Content(schema = @Schema(implementation = VideoResponse.class)))
  })
  public ResponseEntity getVideoByVideoId(@PathVariable(name = "video_id") String videoId) { // Validation required
    VideoResponse result = videoService.searchByVideoId(videoId);

    if(result == null) {
      return ResponseEntity.badRequest().body(
              new BaseResponse(HttpStatus.BAD_REQUEST.value(), ProcessStatus.FAIL, MessageCode.ERROR_REQ_PARAM_VIDEO_ID)
      );
    }
    return ResponseEntity.ok(
            new BaseResponse(result, HttpStatus.OK.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_GET)
    );
  }

  // 영상 video ID 조회 API (Youtube video ID가 주어진 경우)
  @GetMapping("/videos")
  @Operation(summary = "Find a specific video ID", description = "Find a video ID by Youtube video ID.", responses = {
          @ApiResponse(responseCode = "200", description = "Success find video ID!", content = @Content(schema = @Schema(implementation = SavedId.class)))
  })
  public ResponseEntity getVideoIdByYoutubeVideoId(@RequestParam(name = "youtube_video_id") String youtubeVideoId) {
    SavedId result = videoService.searchByYoutubeVideoId(youtubeVideoId);

    return ResponseEntity.ok(
            new BaseResponse(result, HttpStatus.OK.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_GET)
    );
  }

  // 영상 저장 API
  @PostMapping("/videos")
  @Operation(summary = "Create a specific video", description = "Create a video and save.", responses = {
          @ApiResponse(responseCode = "201", description = "Success create video!", content = @Content(schema = @Schema(implementation = SavedId.class)))
  })
  public ResponseEntity saveVideo(@Validated @RequestBody VideoRequest videoRequest) {
    SavedId savedId = new SavedId(videoService.saveVideo(videoRequest));

    return ResponseEntity.created(
            URI.create("/api/v1/videos/".concat(savedId.getId().toString()))
    ).body(
            new BaseResponse(savedId, HttpStatus.CREATED.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_CREATE)
    );
  }
}
