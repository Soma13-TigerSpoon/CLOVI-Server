package Soma.CLOVI.dto.requests;

import lombok.Data;

@Data
public class SearchRequestDto {
    private String channel;
    private long parentCategory;
    private long childCategory;
}
