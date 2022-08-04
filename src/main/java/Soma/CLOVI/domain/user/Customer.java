package Soma.CLOVI.domain.user;

import Soma.CLOVI.domain.Base.BaseTimeEntity;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class Customer extends BaseTimeEntity {
    @Id
    @Column(name="creator_id")
    private Long id;
}
