package Soma.CLOVI.service;

import Soma.CLOVI.dto.requests.ClickRequestDto;
import Soma.CLOVI.dto.response.ClickResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoggingService {

    public ClickResponseDto logClickTracking(ClickRequestDto clickRequestDto) {
        LocalDateTime clickDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        String ipAddress = "";

        ClickResponseDto result = new ClickResponseDto(clickDateTime, ipAddress, clickRequestDto);
        return result;
    }
}
