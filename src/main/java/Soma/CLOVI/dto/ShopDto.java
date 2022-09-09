package Soma.CLOVI.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ShopDto {
    @NotNull
    private String shopName;

    private String description;
    @NotNull
    private String shopUrl;
    @NotNull
    private String shopLogoUrl;
}
