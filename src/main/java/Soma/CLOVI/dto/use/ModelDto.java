package Soma.CLOVI.dto.use;

import Soma.CLOVI.domain.Model;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
public class ModelDto {
    @NotNull
    private String name;
    @NotNull
    private float height_cm;
    @NotNull
    private float weight_kg;
    @NotNull
    private String gender;

    public ModelDto(Model model){
        name = model.getName();
        height_cm = model.getHeight_cm();
        weight_kg = model.getWeight_kg();
        gender = model.getGender().name();
    }
}
