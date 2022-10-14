package Soma.CLOVI.service;

import Soma.CLOVI.dto.requests.ClickRequestDto;
import Soma.CLOVI.dto.response.ClickResponseDto;
import Soma.CLOVI.utils.HttpRequestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;

import Soma.CLOVI.utils.TimeFormatUtils;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoggingService {
    // Record click information
    public ClickResponseDto logClickTracking(ClickRequestDto clickRequestDto) {
        LocalDateTime clickLocalDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        String clickDateTime = TimeFormatUtils.LocalDateTimeToString(clickLocalDateTime);

        String ipAddress = HttpRequestUtils.getClientIpAddress();

        ClickResponseDto result = new ClickResponseDto(clickDateTime, ipAddress, clickRequestDto);
        return result;
    }
}
