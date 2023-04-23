package com.clovi.item.item.service;

import static com.google.common.base.Strings.isNullOrEmpty;

import com.clovi.base.dto.response.MessageCode;
import com.clovi.item.item.Item;
import com.clovi.item.shop.ShopRepository;
import com.clovi.item.shopItem.ShopItem;
import com.clovi.timeShopItem.TimeShopItem;
import com.clovi.videoitem.VideoItem;
import com.clovi.model.Model;
import com.clovi.timeframe.TimeFrame;
import com.clovi.item.info.ItemInfo;
import com.clovi.item.shop.Shop;
import com.clovi.timeShopItem.dto.request.TimeShopItemRequestDto;
import com.clovi.video.Video;
import com.clovi.item.item.dto.request.ItemCreateRequest;
import com.clovi.item.shopItem.ShopItemRepository;
import com.clovi.item.shopItem.dto.request.ShopItemRequestDto;
import com.clovi.model.ModelRepository;
import com.clovi.item.category.repository.CategoryRepository;
import com.clovi.item.item.repository.ItemRepository;
import com.clovi.item.info.ItemInfoRepository;
import com.clovi.timeframe.repository.TimeFrameRepository;
import com.clovi.video.repository.VideoRepository;
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
  public Long save(TimeShopItemRequestDto timeShopItemRequestDto) {
    // logging, warning 등 이런 에러의 단계도 custom 할 수 있기 때문에 이걸 찾아보고
    // APM 검색해서 한번 조사해보자
    // 프로메테우스 데이터 -> 그라파나 -> 대쉬보드화 하기 !!! (APM 환경 만들기)
    Model model = modelRepository.findById(timeShopItemRequestDto.getModelId()).orElseThrow(
        () -> new IllegalArgumentException("해당 모델이 없습니다. id=" + timeShopItemRequestDto.getModelId()));
    Video video = videoRepository.findById(timeShopItemRequestDto.getVideoId()).orElseThrow(
        () -> new IllegalArgumentException("해당 비디오가 없습니다. id=" + timeShopItemRequestDto.getVideoId()));

    Long capturePoint = timeShopItemRequestDto.getStartTime();
    TimeFrame timeFrame = timeFrameRepository.findByVideoAndCapturePoint(video,capturePoint).orElse(
        new TimeFrame(capturePoint, model, video)
    );

    //부모 아이템 조회 --> null 이 아닌 경우에
    ItemInfo parentItemInfo = timeShopItemRequestDto.getParentId() < 0 ? null : itemInfoRepository.findById(timeShopItemRequestDto.getParentId()).orElseThrow(
        () -> new IllegalArgumentException(MessageCode.ERROR_REQ_PARAM_ITEM_ID.getMessage())
    ) ;

    // 상품은 이름과 색깔, 브랜드로 있는지 파악 후 없으면 카테고리를 조회 해서 새로운 아이템으로 저장
    ItemInfo itemInfo = itemInfoRepository.findByNameAndBrandAndColor(timeShopItemRequestDto.getName(), timeShopItemRequestDto.getBrand(), timeShopItemRequestDto.getColor()).orElse(
        new ItemInfo(timeShopItemRequestDto,categoryRepository.findById(timeShopItemRequestDto.getCategoryId()).orElseThrow(
            () -> new IllegalArgumentException(MessageCode.ERROR_REQ_PARAM_CATEGORY_ID.getMessage())
        ), parentItemInfo)
    );

    itemInfoRepository.save(itemInfo);

    for (ShopItemRequestDto shopItemRequestDto : timeShopItemRequestDto.getShopItems()) {
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
    if(!isNullOrEmpty(timeShopItemRequestDto.getAffLink()) && ResourceUtils.isUrl(timeShopItemRequestDto.getAffLink())){
      String hostname = timeShopItemRequestDto.getAffHostname();
      // host 이름으로 shop 찾고 없으면 새로 만들어서 추가
      Shop shop = shopRepository.findByHostname(hostname).orElse(
          new Shop(hostname)
      );
      ShopItem affShopItem = shopItemRepository.findByShopItemUrlAndPrice(timeShopItemRequestDto.getAffLink(), timeShopItemRequestDto.getAffPrice()).orElse(
          new ShopItem(new ShopItemRequestDto(timeShopItemRequestDto.getAffLink(), timeShopItemRequestDto.getAffPrice()), itemInfo, shop)
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
    Item item = new Item(new ItemCreateRequest(itemInfo.getId(), timeShopItemRequestDto.getSize(), timeShopItemRequestDto.getColor(), itemInfo.getImgUrl()),itemInfo, 1L);
    Item saved = ItemRepository.save(item);
    timeFrame.addItem(new TimeShopItem(timeFrame, saved, shopItem));

    videoRepository.save(video);
    VideoItem videoItem = new VideoItem(video, item);
    video.addVideoItem(videoItem);


    video.addTimeFrame(timeFrame);

    return itemInfo.getId();
  }
}
