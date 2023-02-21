package com.clovi.dto.requests;

import lombok.Data;

@Data
public class SearchRequestDto {
    private String keyword;
    private String channel;
    private long parentCategory;
    private long childCategory;
}
