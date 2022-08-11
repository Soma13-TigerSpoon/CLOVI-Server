package Soma.CLOVI.dto.use;

import Soma.CLOVI.domain.Model;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ModelResponseDto {

    private Long id;
    private String name;
    private float height_cm;
    private float weight_kg;
    private String gender;

    public ModelResponseDto(Model model){
        this.id = model.getId();
        name = model.getName();
        height_cm = model.getHeight_cm();
        weight_kg = model.getWeight_kg();
        gender = model.getGender().name();
    }
}
