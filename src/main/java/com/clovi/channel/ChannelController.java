package com.clovi.channel;

import com.clovi.base.dto.response.BaseResponse;
import com.clovi.base.dto.response.MessageCode;
import com.clovi.base.dto.response.ProcessStatus;
import com.clovi.channel.dto.response.ChannelResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ChannelController {
    private final ChannelService channelService;

    @GetMapping("/channels")
    public ResponseEntity getAllChannels() {
        List<ChannelResponseDto> result = channelService.findAllChannels();

        return ResponseEntity.ok(
                new BaseResponse(result, HttpStatus.OK.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_GET_LIST)
        );
    }
}
