package com.clovi.app.item.service;

import static com.google.common.base.Strings.isNullOrEmpty;

import com.clovi.app.base.dto.response.MessageCode;
import com.clovi.app.item.domain.Item;
import com.clovi.app.item.dto.request.ItemCreateRequest;
import com.clovi.app.shop.domain.Shop;
import com.clovi.app.shop.repository.ShopRepository;
import com.clovi.app.shopItem.domain.ShopItem;
import com.clovi.app.shopItem.repository.ShopItemRepository;
import com.clovi.app.model.domain.Model;
import com.clovi.app.model.repository.ModelRepository;
import com.clovi.app.shopItem.dto.request.ShopItemRequest;
import com.clovi.app.timeShopItem.dto.request.TimeShopItemRequest;
import com.clovi.app.timeframe.domain.Timeframe;
import com.clovi.app.timeframe.repository.TimeframeRepository;
import com.clovi.app.video.domain.Video;
import com.clovi.app.video.repository.VideoRepository;
import com.clovi.app.videoItem.domain.VideoItem;
import com.clovi.app.timeShopItem.domain.TimeShopItem;
import com.clovi.app.itemInfo.domain.ItemInfo;
import com.clovi.app.category.repository.CategoryRepository;
import com.clovi.app.item.repository.ItemRepository;
import com.clovi.app.itemInfo.repository.ItemInfoRepository;
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
  private final TimeframeRepository timeFrameRepository;
  private final ModelRepository modelRepository;
  private final CategoryRepository categoryRepository;
  @Transactional
  public Long save(TimeShopItemRequest timeShopItemRequest) {
    // logging, warning 등 이런 에러의 단계도 custom 할 수 있기 때문에 이걸 찾아보고
    // APM 검색해서 한번 조사해보자
    // 프로메테우스 데이터 -> 그라파나 -> 대쉬보드화 하기 !!! (APM 환경 만들기)
    Model model = modelRepository.findById(timeShopItemRequest.getModelId()).orElseThrow(
        () -> new IllegalArgumentException("해당 모델이 없습니다. id=" + timeShopItemRequest.getModelId()));
    Video video = videoRepository.findById(timeShopItemRequest.getVideoId()).orElseThrow(
        () -> new IllegalArgumentException("해당 비디오가 없습니다. id=" + timeShopItemRequest.getVideoId()));

    Long capturePoint = timeShopItemRequest.getStartTime();
    Timeframe timeFrame = timeFrameRepository.findByVideoAndCapturePoint(video,capturePoint).orElse(
        new Timeframe(capturePoint, model, video)
    );

    //부모 아이템 조회 --> null 이 아닌 경우에
    ItemInfo parentItemInfo = timeShopItemRequest.getParentId() < 0 ? null : itemInfoRepository.findById(timeShopItemRequest.getParentId()).orElseThrow(
        () -> new IllegalArgumentException(MessageCode.ERROR_REQ_PARAM_ITEM_ID.getMessage())
    ) ;

    // 상품은 이름과 색깔, 브랜드로 있는지 파악 후 없으면 카테고리를 조회 해서 새로운 아이템으로 저장
    ItemInfo itemInfo = itemInfoRepository.findByNameAndBrandAndColor(timeShopItemRequest.getName(), timeShopItemRequest.getBrand(), timeShopItemRequest.getColor()).orElse(
        new ItemInfo(timeShopItemRequest,categoryRepository.findById(timeShopItemRequest.getCategoryId()).orElseThrow(
            () -> new IllegalArgumentException(MessageCode.ERROR_REQ_PARAM_CATEGORY_ID.getMessage())
        ), parentItemInfo)
    );

    itemInfoRepository.save(itemInfo);

    for (ShopItemRequest shopItemRequest : timeShopItemRequest.getShopItems()) {
      String hostname = shopItemRequest.getHostname();
      Shop shop = shopRepository.findByHostname(hostname).orElse(
          new Shop(hostname)
      );
      ShopItem shopItem = shopItemRepository.findByShopItemUrl(shopItemRequest.getShopItemUrl()).orElse(
          new ShopItem(shopItemRequest, itemInfo, shop)
      );
      shop.addShopItem(shopItem);
      shopRepository.save(shop);
      itemInfo.addShopItem(shopItem);
    };
    ShopItem shopItem;
    //제휴링크가 있는지, URL 형식이 맞는지 확인
    if(!isNullOrEmpty(timeShopItemRequest.getAffLink()) && ResourceUtils.isUrl(timeShopItemRequest.getAffLink())){
      String hostname = timeShopItemRequest.getAffHostname();
      // host 이름으로 shop 찾고 없으면 새로 만들어서 추가
      Shop shop = shopRepository.findByHostname(hostname).orElse(
          new Shop(hostname)
      );
      ShopItem affShopItem = shopItemRepository.findByShopItemUrlAndPrice(timeShopItemRequest.getAffLink(), timeShopItemRequest.getAffPrice()).orElse(
          new ShopItem(new ShopItemRequest(timeShopItemRequest.getAffLink(), timeShopItemRequest.getAffPrice()), itemInfo, shop)
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
    Item item = new Item(new ItemCreateRequest(itemInfo.getId(), timeShopItemRequest.getSize(), timeShopItemRequest.getColor(), itemInfo.getImgUrl()),itemInfo, 1L);
    Item saved = ItemRepository.save(item);
    timeFrame.addItem(new TimeShopItem(timeFrame, saved, shopItem));

    videoRepository.save(video);
    VideoItem videoItem = new VideoItem(video, item);
    video.addVideoItem(videoItem);


    video.addTimeFrame(timeFrame);

    return itemInfo.getId();
  }
}
