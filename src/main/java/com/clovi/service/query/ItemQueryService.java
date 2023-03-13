package com.clovi.service.query;

import static com.google.common.base.Strings.isNullOrEmpty;

import com.clovi.api.response.MessageCode;
import com.clovi.domain.ManyToMany.ShopItem;
import com.clovi.domain.ManyToMany.TimeShopItem;
import com.clovi.domain.ManyToMany.VideoItem;
import com.clovi.domain.Model;
import com.clovi.domain.TimeFrame;
import com.clovi.domain.item.Item;
import com.clovi.domain.item.ItemInfo;
import com.clovi.domain.shop.Shop;
import com.clovi.domain.youtube.Video;
import com.clovi.dto.requests.item.detail.ItemCreateRequest;
import com.clovi.dto.requests.ShopItemRequestDto;
import com.clovi.dto.requests.TimeItemRequestDto;
import com.clovi.repository.*;
import com.clovi.repository.Category.CategoryRepository;
import com.clovi.repository.Item.ItemRepository;
import com.clovi.repository.Item.ItemInfoRepository;
import com.clovi.repository.Video.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemQueryService {
  private final ItemInfoRepository itemInfoRepository;
  private final ItemRepository ItemRepository;
  private final VideoRepository videoRepository;
  private final ShopRepository shopRepository;

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
    TimeFrame timeFrame = timeFrameRepository.findByVideoAndCapturePoint(video,capturePoint).orElse(
        new TimeFrame(capturePoint, model, video)
    );

    //부모 아이템 조회 --> null 이 아닌 경우에
    ItemInfo parentItemInfo = timeItemRequestDto.getParentId() < 0 ? null : itemInfoRepository.findById(timeItemRequestDto.getParentId()).orElseThrow(
        () -> new IllegalArgumentException(MessageCode.ERROR_REQ_PARAM_ITEM_ID.getMessage())
    ) ;

    // 상품은 이름과 색깔, 브랜드로 있는지 파악 후 없으면 카테고리를 조회 해서 새로운 아이템으로 저장
    ItemInfo itemInfo = itemInfoRepository.findByNameAndBrandAndColor(timeItemRequestDto.getName(),timeItemRequestDto.getBrand(),timeItemRequestDto.getColor()).orElse(
        new ItemInfo(timeItemRequestDto,categoryRepository.findById(timeItemRequestDto.getCategoryId()).orElseThrow(
            () -> new IllegalArgumentException(MessageCode.ERROR_REQ_PARAM_CATEGORY_ID.getMessage())
        ), parentItemInfo)
    );

    itemInfoRepository.save(itemInfo);

    for (ShopItemRequestDto shopItemRequestDto : timeItemRequestDto.getShopItems()) {
      String hostname = shopItemRequestDto.getHostname();
      Shop shop = shopRepository.findByHostname(hostname).orElse(
          new Shop(hostname)
      );
      ShopItem shopItem = shopItemRepository.findByShopItemUrl(shopItemRequestDto.getShopItemUrl()).orElse(
          new ShopItem(shopItemRequestDto, itemInfo, shop)
      );
      shop.addShopItem(shopItem);
      shopRepository.save(shop);
      itemInfo.addShopItem(shopItem);
    };
    ShopItem shopItem;
    //제휴링크가 있는지, URL 형식이 맞는지 확인
    if(!isNullOrEmpty(timeItemRequestDto.getAffLink()) && ResourceUtils.isUrl(timeItemRequestDto.getAffLink())){
      String hostname = timeItemRequestDto.getAffHostname();
      // host 이름으로 shop 찾고 없으면 새로 만들어서 추가
      Shop shop = shopRepository.findByHostname(hostname).orElse(
          new Shop(hostname)
      );
      ShopItem affShopItem = shopItemRepository.findByShopItemUrlAndPrice(timeItemRequestDto.getAffLink(),timeItemRequestDto.getAffPrice()).orElse(
          new ShopItem(new ShopItemRequestDto(timeItemRequestDto.getAffLink(),timeItemRequestDto.getAffPrice()), itemInfo, shop)
      );
      shopItem = affShopItem;
      shopItemRepository.save(shopItem);
    }
    else{
      shopItem = null;
    }
    for(TimeShopItem timeShopItem : timeFrame.getItems()){
      if( timeShopItem.getItem().getId() == itemInfo.getId() ) {
        return itemInfo.getId();
      }
    }
    Item item = new Item(new ItemCreateRequest(itemInfo.getId(),timeItemRequestDto.getSize(),timeItemRequestDto.getColor()),itemInfo, 1L);
    Item saved = ItemRepository.save(item);
    timeFrame.addItem(new TimeShopItem(timeFrame, saved, shopItem));

    videoRepository.save(video);
    VideoItem videoItem = new VideoItem(video, item);
    video.addVideoItem(videoItem);


    video.addTimeFrame(timeFrame);

    return itemInfo.getId();
  }
}
