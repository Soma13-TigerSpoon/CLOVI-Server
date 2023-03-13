package com.clovi.api.controller;

import com.clovi.api.response.BaseResponse;
import com.clovi.api.response.MessageCode;
import com.clovi.api.response.ProcessStatus;
import com.clovi.dto.requests.ClickRequestDto;
import com.clovi.dto.response.ClickResponseDto;
import com.clovi.service.LoggingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LoggingController {
    private final LoggingService loggingService;
    private final Logger logger = LoggerFactory.getLogger("User Click Event!!");

    @PostMapping("/click")
    public ResponseEntity recordClickInfo(@RequestBody ClickRequestDto clickRequestDto) {
        ClickResponseDto result = loggingService.logClickTracking(clickRequestDto);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String clickInfo = mapper.writeValueAsString(result);
            logger.info(clickInfo);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        // String clickInfo = result.toString();
        // logger.info(clickInfo);

        return ResponseEntity.ok(
                new BaseResponse(HttpStatus.OK.value(), ProcessStatus.SUCCESS, MessageCode.SUCCESS_GET)
        );
    }
}
