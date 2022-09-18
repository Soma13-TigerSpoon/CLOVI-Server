package Soma.CLOVI.dto.response;

import Soma.CLOVI.domain.Model;
import lombok.Getter;


@Getter
public class ModelResponseDto {

    private Long id;
    private String name;
    private float height_cm;
    private float weight_kg;
    private String gender;

    public ModelResponseDto(Model model){
        this.id = model.getId();
        this.name = model.getName();
        this.height_cm = model.getHeight_cm();
        this.weight_kg = model.getWeight_kg();
        this.gender = model.getGender().name();
    }
}
