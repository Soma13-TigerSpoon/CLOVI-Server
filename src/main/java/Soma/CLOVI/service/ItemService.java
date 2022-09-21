package Soma.CLOVI.service;

import Soma.CLOVI.api.response.MessageCode;
import Soma.CLOVI.domain.ManyToMany.ShopItem;
import Soma.CLOVI.domain.ManyToMany.TimeItem;
import Soma.CLOVI.domain.ManyToMany.VideoItem;
import Soma.CLOVI.domain.Model;
import Soma.CLOVI.domain.TimeFrame;
import Soma.CLOVI.domain.category.Category;
import Soma.CLOVI.domain.item.Item;
import Soma.CLOVI.domain.shop.Shop;
import Soma.CLOVI.domain.youtube.Video;
import Soma.CLOVI.dto.requests.ShopItemRequestDto;
import Soma.CLOVI.dto.response.ItemResponseDto;
import Soma.CLOVI.dto.requests.TimeItemRequestDto;
import Soma.CLOVI.repository.Category.CategoryRepository;
import Soma.CLOVI.repository.Item.ItemRepository;
import Soma.CLOVI.repository.Item.ItemRepositoryImpl;
import Soma.CLOVI.repository.ModelRepository;
import Soma.CLOVI.repository.ShopItemRepository;
import Soma.CLOVI.repository.ShopRepository;
import Soma.CLOVI.repository.TimeFrameRepository;
import Soma.CLOVI.repository.Video.VideoRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {


  private final ItemRepository itemRepository;
  private final VideoRepository videoRepository;
  private final ShopRepository shopRepository;
  private final ShopItemRepository shopItemRepository;
  private final TimeFrameRepository timeFrameRepository;
  private final ModelRepository modelRepository;
  private final ItemRepositoryImpl itemRepositoryImpl;

  private final CategoryRepository categoryRepository;

  public List<ItemResponseDto> getItems(List<Long> itemIdList) {
    return itemRepositoryImpl.searchByIdList(itemIdList).stream().
        map(item -> new ItemResponseDto(item)).collect(Collectors.toList());
  }

  @Transactional
  public Long save(TimeItemRequestDto timeItemRequestDto) {

    Model model = modelRepository.findById(timeItemRequestDto.getModelId()).orElseThrow(
        () -> new IllegalArgumentException("해당 모델이 없습니다. id=" + timeItemRequestDto.getModelId()));
    Video video = videoRepository.findById(timeItemRequestDto.getVideoId()).orElseThrow(
        () -> new IllegalArgumentException("해당 비디오가 없습니다. id=" + timeItemRequestDto.getVideoId()));

    Long capturePoint = timeItemRequestDto.getStartTime();
    TimeFrame timeFrame = timeFrameRepository.findByCapturePointBetween(capturePoint - 2,
        capturePoint + 2).orElse(
        new TimeFrame(capturePoint, model, video)
    );
    //카테고리 조회
    Category category = categoryRepository.findById(timeItemRequestDto.getCategoryId()).orElseThrow(
        () -> new IllegalArgumentException(MessageCode.ERROR_REQ_PARAM_CATEGORY_ID.getMessage())
    );

    //부모 아이템 조회 --> null이 아닌 경우에
    Item parentItem = timeItemRequestDto.getParentId() < 0 ? null : itemRepository.findById(timeItemRequestDto.getParentId()).orElseThrow(
        () -> new IllegalArgumentException(MessageCode.ERROR_REQ_PARAM_ITEM_ID.getMessage())
    ) ;


    // 상품은 이름과 색깔로 있는지 파악
    Item item = itemRepository.findByNameAndColor(timeItemRequestDto.getName(),
        timeItemRequestDto.getColor()).orElse(
        new Item(timeItemRequestDto,category,parentItem)
    );
    System.out.println(category.equals(null));
    itemRepository.save(item);

    for (ShopItemRequestDto shopItemRequestDto : timeItemRequestDto.getShopItems()) {
      String hostname = shopItemRequestDto.getHostname();
      Shop shop = shopRepository.findByHostname(hostname).orElse(
          new Shop(hostname)
      );
      ShopItem shopItem = shopItemRepository.findByShopUrl(shopItemRequestDto.getShopUrl()).orElse(
          new ShopItem(shopItemRequestDto, item, shop)
      );
      shop.addShopItem(shopItem);
      shopRepository.save(shop);
      item.addShopItem(shopItem);
    }

    timeFrame.addItem(new TimeItem(timeFrame, item));

    videoRepository.save(video);
    video.addVideoItem(new VideoItem(video, item));
    video.addTimeFrame(timeFrame);

    return item.getId();
  }

  public Item getById(Long itemId) {
    return itemRepository.findById(itemId).orElseThrow(
        () -> new IllegalArgumentException("해당 Id를 가진 아이템이 없습니다. id=" + itemId));
  }
}
