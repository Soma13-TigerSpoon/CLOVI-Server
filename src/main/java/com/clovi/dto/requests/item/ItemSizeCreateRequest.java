package com.clovi.dto.requests.item;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(name = "아이템 사이즈 정보 생성 요청")
public class ItemSizeCreateRequest {
    private String size;
}
