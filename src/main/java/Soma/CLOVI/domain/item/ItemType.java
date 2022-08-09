package Soma.CLOVI.domain.item;

import lombok.Getter;

@Getter
public enum ItemType {
    TOP(2), PANTS(3), CAP(1), SHOES(4);

    private int order;

    ItemType(int order) {
        this.order = order;
    }
}
