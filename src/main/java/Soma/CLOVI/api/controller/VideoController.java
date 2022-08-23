package Soma.CLOVI.api.controller;

import Soma.CLOVI.api.response.BaseResponse;
import Soma.CLOVI.api.response.MessageCode;
import Soma.CLOVI.api.response.ProcessStatus;
import Soma.CLOVI.dto.use.*;
import Soma.CLOVI.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;
    @GetMapping("/api/v1/videos")
    public BaseResponse getVideoV1(@RequestParam("videoUrl") String videoUrl){
        VideoResponseDto result = videoService.search(videoUrl);
        if(result == null){
            return new BaseResponse(HttpStatus.BAD_REQUEST,ProcessStatus.FAIL, MessageCode.ERROR_REQ_PARAM_VIDEO_ID);
        }
        return new BaseResponse(result, HttpStatus.OK, ProcessStatus.SUCCESS, MessageCode.SUCCESS_GET);
    }
    @PostMapping("api/v1/videos")
    public BaseResponse saveVideoV1(@RequestBody VideoRequestDto videoRequestDto){
        Long result = videoService.save(videoRequestDto);
        if(result == null){
            return new BaseResponse(HttpStatus.BAD_REQUEST,ProcessStatus.FAIL, MessageCode.ERROR_REQ_PARAM_VIDEO_ID);
        }
        return new BaseResponse(result, HttpStatus.OK, ProcessStatus.SUCCESS, MessageCode.SUCCESS_CREATE);
    }
}
