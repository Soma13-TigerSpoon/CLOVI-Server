package Soma.CLOVI.service.query;

import Soma.CLOVI.domain.Model;
import Soma.CLOVI.domain.TimeFrame;
import Soma.CLOVI.domain.item.Item;
import Soma.CLOVI.domain.manytomany.ShopItem;
import Soma.CLOVI.domain.manytomany.TimeShopItem;
import Soma.CLOVI.domain.manytomany.VideoItem;
import Soma.CLOVI.domain.youtube.Video;
import Soma.CLOVI.dto.requests.TimeShopItemRequestDto;
import Soma.CLOVI.repository.Category.CategoryRepository;
import Soma.CLOVI.repository.Item.ItemRepository;
import Soma.CLOVI.repository.ModelRepository;
import Soma.CLOVI.repository.ShopItemRepository;
import Soma.CLOVI.repository.ShopRepository;
import Soma.CLOVI.repository.TimeFrameRepository;
import Soma.CLOVI.repository.TimeShopItemRepository;
import Soma.CLOVI.repository.Video.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemQueryService {
  private final ItemRepository itemRepository;
  private final ShopItemRepository shopItemRepository;
  private final TimeFrameRepository timeFrameRepository;

  private final TimeShopItemRepository timeShopItemRepository;
  @Transactional
  public Long save(TimeShopItemRequestDto timeShopItemRequestDto) {
    // logging, warning 등 이런 에러의 단계도 custom 할 수 있기 때문에 이걸 찾아보고
    // APM 검색해서 한번 조사해보자
    // 프로메테우스 데이터 -> 그라파나 -> 대쉬보드화 하기 !!! (APM 환경 만들기)
    Item item = itemRepository.findById(timeShopItemRequestDto.getItemId()).orElseThrow(
        () -> new IllegalArgumentException("해당 아이템이 없습니다. id=" + timeShopItemRequestDto.getItemId()));
    TimeFrame timeFrame = timeFrameRepository.findById(timeShopItemRequestDto.getTimeId()).orElseThrow(
        () -> new IllegalArgumentException("해당 시간이 없습니다. id=" + timeShopItemRequestDto.getTimeId()));
    ShopItem shopItem = shopItemRepository.findById(timeShopItemRequestDto.getShopItemId()).orElseThrow(
        () -> new IllegalArgumentException("해당 구매처가 없습니다. id=" + timeShopItemRequestDto.getShopItemId()));

    TimeShopItem timeShopItem = new TimeShopItem(timeFrame,item,shopItem);

    timeShopItemRepository.save(timeShopItem);

    return timeShopItem.getId();
  }
}
