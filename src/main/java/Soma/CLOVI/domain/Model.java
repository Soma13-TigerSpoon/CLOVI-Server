package Soma.CLOVI.domain;

import Soma.CLOVI.domain.Base.BaseEntity;
import Soma.CLOVI.domain.Base.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Model extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id")
    private Long id;

    private String name;
    private float height_cm;
    private float weight_kg;

    @Enumerated(EnumType.STRING)
    private Gender gender;


    @Builder
    public Model(String name, float height_cm, float weight_kg, Gender gender) {
        this.name = name;
        this.height_cm = height_cm;
        this.weight_kg = weight_kg;
        this.gender = gender;
    }
}
