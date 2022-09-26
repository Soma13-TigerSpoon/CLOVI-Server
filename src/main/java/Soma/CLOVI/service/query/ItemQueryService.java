package Soma.CLOVI.service.query;

import Soma.CLOVI.api.response.MessageCode;
import Soma.CLOVI.domain.AffiliateLink;
import Soma.CLOVI.domain.ManyToMany.ShopItem;
import Soma.CLOVI.domain.ManyToMany.TimeItemAffiliationLink;
import Soma.CLOVI.domain.ManyToMany.VideoItem;
import Soma.CLOVI.domain.Model;
import Soma.CLOVI.domain.TimeFrame;
import Soma.CLOVI.domain.category.Category;
import Soma.CLOVI.domain.item.Item;
import Soma.CLOVI.domain.shop.Shop;
import Soma.CLOVI.domain.youtube.Video;
import Soma.CLOVI.dto.requests.ShopItemRequestDto;
import Soma.CLOVI.dto.requests.TimeItemRequestDto;
import Soma.CLOVI.repository.AffiliationLinkRepository;
import Soma.CLOVI.repository.Category.CategoryRepository;
import Soma.CLOVI.repository.Item.ItemRepository;
import Soma.CLOVI.repository.ModelRepository;
import Soma.CLOVI.repository.ShopItemRepository;
import Soma.CLOVI.repository.ShopRepository;
import Soma.CLOVI.repository.TimeFrameRepository;
import Soma.CLOVI.repository.Video.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemQueryService {

  private final ItemRepository itemRepository;
  private final VideoRepository videoRepository;
  private final ShopRepository shopRepository;

  private final AffiliationLinkRepository affiliationLinkRepository;
  private final ShopItemRepository shopItemRepository;
  private final TimeFrameRepository timeFrameRepository;
  private final ModelRepository modelRepository;
  private final CategoryRepository categoryRepository;
  @Transactional
  public Long save(TimeItemRequestDto timeItemRequestDto) {
    // logging, warning 등 이런 에러의 단계도 custom 할 수 있기 때문에 이걸 찾아보고
    // APM 검색해서 한번 조사해보자
    // 프로메테우스 데이터 -> 그라파나 -> 대쉬보드화 하기 !!! (APM 환경 만들기)
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

    //부모 아이템 조회 --> null 이 아닌 경우에
    Item parentItem = timeItemRequestDto.getParentId() < 0 ? null : itemRepository.findById(timeItemRequestDto.getParentId()).orElseThrow(
        () -> new IllegalArgumentException(MessageCode.ERROR_REQ_PARAM_ITEM_ID.getMessage())
    ) ;


    // 상품은 이름과 색깔로 있는지 파악
    Item item = itemRepository.findByNameAndColor(timeItemRequestDto.getName(),
        timeItemRequestDto.getColor()).orElse(
        new Item(timeItemRequestDto,category,parentItem)
    );
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
    };

    AffiliateLink affiliateLink;
    try{
      affiliateLink = new AffiliateLink(timeItemRequestDto.getAffLink(),
          timeItemRequestDto.getAffPrice());
      affiliationLinkRepository.save(affiliateLink);
    }catch (Exception e){
      affiliateLink = null;
    }

    timeFrame.addItem(new TimeItemAffiliationLink(timeFrame, item, affiliateLink));

    videoRepository.save(video);
    VideoItem videoItem = new VideoItem(video, item);
    video.addVideoItem(videoItem);

    video.addTimeFrame(timeFrame);

    return item.getId();
  }
}
