package com.clovi.app.model.dto.response;

import com.clovi.app.model.Model;
import lombok.Getter;


@Getter
public class ModelResponse {

    private Long id;
    private String name;
    private float height_cm;
    private float weight_kg;
    private String gender;

    public ModelResponse(Model model){
        this.id = model.getId();
        this.name = model.getName();
        this.height_cm = model.getHeight_cm();
        this.weight_kg = model.getWeight_kg();
        this.gender = model.getGender().name();
    }
}
