package Soma.CLOVI.service;

import Soma.CLOVI.domain.item.Item;
import Soma.CLOVI.dto.use.ItemResponseDto;
import Soma.CLOVI.repository.Item.ItemRepository;
import Soma.CLOVI.repository.Item.ItemRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemRepositoryImpl itemRepositoryImpl;

    public List<ItemResponseDto> getItems(List<Long> itemIdList){
        return itemRepositoryImpl.searchByIdList(itemIdList).stream().
                map(item -> new ItemResponseDto(item)).collect(Collectors.toList());
    }
}
