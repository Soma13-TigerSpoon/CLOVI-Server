package Soma.CLOVI.domain.item;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;

@Getter
public enum ItemType {
  TOP(2), PANTS(3), CAP(1), SHOES(4);

  public static final Map<Integer, ItemType> types =
      Collections.unmodifiableMap(Stream.of(values())
          .collect(Collectors.toMap(ItemType::getOrder, Function.identity())));
  private int order;

  ItemType(int order) {
    this.order = order;
  }
}
