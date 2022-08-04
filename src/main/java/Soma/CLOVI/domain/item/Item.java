package Soma.CLOVI.domain.item;

import Soma.CLOVI.domain.Base.BaseEntity;
import Soma.CLOVI.domain.TimeFrame;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(indexes = {
        @Index(name = "i_item_name", columnList = "itemName")
})
@Getter @Setter

public class Item extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String itemName;

    private String description;
    private String color;
    private String size;
    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    public Item() {

    }

    @Builder
    public Item(String itemName, String description, String color, String size, ItemType itemType) {
        this.itemName = itemName;
        this.description = description;
        this.color = color;
        this.size = size;
        this.itemType = itemType;
    }
}
