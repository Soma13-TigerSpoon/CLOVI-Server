package com.clovi.api.controller;

import com.clovi.api.response.BaseResponse;
import com.clovi.api.response.MessageCode;
import com.clovi.api.response.ProcessStatus;
import com.clovi.dto.response.IdResponseDto;
import com.clovi.dto.requests.VideoRequestDto;
import com.clovi.dto.response.VideoResponseDto;
import com.clovi.service.VideoService;
import com.clovi.service.query.VideoQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VideoController {
  private final VideoService videoService;
  private final VideoQueryService videoQueryService;

  // Validation required
  @GetMapping("/api/v1/videos")
  public ResponseEntity getVideoV1(@RequestParam("videoUrl") String videoUrl) {
    VideoResponseDto result = videoService.search(videoUrl);

    if(result == null) {
      return ResponseEntity.badRequest().body(
              new BaseResponse(HttpStatus.BAD_REQUEST.value(), ProcessStatus.FAIL, MessageCode.ERROR_REQ_PARAM_VIDEO_ID)
      );
    }

    return ResponseEntity.ok(
            new BaseResponse(result, HttpStatus.OK.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_GET)
    );
  }

  @PostMapping("api/v1/videos")
  public ResponseEntity saveVideoV1(@RequestBody VideoRequestDto videoRequestDto) {
    Long result = videoQueryService.save(videoRequestDto);

    if(result == null) {
      return ResponseEntity.badRequest().body(
              new BaseResponse(HttpStatus.BAD_REQUEST.value(), ProcessStatus.FAIL, MessageCode.ERROR_REQ_PARAM_VIDEO_ID)
      );
    }

    return new ResponseEntity<>(
            new BaseResponse(new IdResponseDto(result), HttpStatus.CREATED.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_CREATE),
            HttpStatus.CREATED
    );
  }
}
