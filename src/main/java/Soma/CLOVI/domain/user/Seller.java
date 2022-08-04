package Soma.CLOVI.domain.user;

import Soma.CLOVI.domain.Base.BaseTimeEntity;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Seller extends BaseTimeEntity {
    @Id @GeneratedValue
    @Column(name = "seller_id")
    private Long id;

    private String name;

}
