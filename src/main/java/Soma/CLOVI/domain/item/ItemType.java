package Soma.CLOVI.domain.item;

import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum ItemType {
    TOP(2), PANTS(3), CAP(1), SHOES(4);

    private int order;

    ItemType(int order) {
        this.order = order;
    }

    public static final Map<Integer, ItemType> types =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(ItemType::getOrder, Function.identity())));
}
