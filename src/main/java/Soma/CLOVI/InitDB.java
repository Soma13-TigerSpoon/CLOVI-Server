package Soma.CLOVI;

import Soma.CLOVI.domain.Gender;
import Soma.CLOVI.domain.Model;
import Soma.CLOVI.domain.ShopItem;
import Soma.CLOVI.domain.TimeFrame;
import Soma.CLOVI.domain.item.Item;
import Soma.CLOVI.domain.item.ItemType;
import Soma.CLOVI.domain.shop.Shop;
import Soma.CLOVI.domain.user.YoutubeCreator;
import Soma.CLOVI.domain.youtube.Channel;
import Soma.CLOVI.domain.youtube.Video;
import Soma.CLOVI.dto.use.SoldOutStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDB {
    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;
        public void dbInit() {


            Model model = Model.builder()
                    .gender(Gender.M)
                    .name("핏더사이즈")
                    .weight_kg(88.0f)
                    .height_cm(183.0f)
                    .build();

            em.persist(model);
            List<ShopItem> shopItemList1 = new ArrayList<>();
            List<ShopItem> shopItemList2 = new ArrayList<>();


            Shop lotteOnShop = Shop.builder()
                    .shopLogoUrl("https://ko.m.wikipedia.org/wiki/%ED%8C%8C%EC%9D%BC:Lotte_%22Value_Line%22_logo.svg")
                    .shopName("LotteOn").build();

            Shop trenbeShop = Shop.builder()
                    .shopLogoUrl("https://www.techm.kr/news/thumbnail/202008/74445_67428_1335_v150.jpg")
                    .shopName("Trenbe").build();

            Shop mustitShop = Shop.builder()
                    .shopLogoUrl("https://s3.ap-northeast-2.amazonaws.com/mustit-ux/img/m/m_common/ban_share_common.png")
                    .shopName("Mustit").build();

            Shop musinsaShop = Shop.builder()
                    .shopLogoUrl("https://pds.saramin.co.kr/company/logo/202202/21/r7n4lg_iaut-i637x_logo.JPG")
                    .shopName("Musinsa").build();

            em.persist(lotteOnShop);
            em.persist(trenbeShop);
            em.persist(musinsaShop);
            em.persist(mustitShop);

            Item item1 = Item.builder()
                    .itemName("갤러리 디파트먼트 Vintage Souvenir 반팔티")
                    .color("Black")
                    .size("XXL")
                    .itemType(ItemType.TOP)
                    .imgUrl("https://www.lotteon.com/m/product/LO1865153271?sitmNo=LO1865153271_1865153272&mall_no=1&dp_infw_cd=SCH%EA%B0%A4%EB%9F%AC%EB%A6%AC%EB%94%94%ED%8C%8C%ED%8A%B8%EB%A8%BC%ED%8A%B8")
                    .build();

            Item item2 = Item.builder()
                    .itemName("나누슈카 로고 볼캡")
                    .color("Black")
                    .size("free")
                    .itemType(ItemType.CAP)
                    .imgUrl("https://cdn.mustit.co.kr/lib/upload/product/luxboy/2022/05/1653037065-72.jpeg/_dims_/resize/500x500/extent/500x500")
                    .build();

            Item item3 = Item.builder()
                    .itemName("페이탈리즘 #0280 mer two tuck wide indigo")
                    .color("Indigo Blue")
                    .size("48")
                    .itemType(ItemType.PANTS)
                    .imgUrl("https://image.msscdn.net/images/prd_img/20220519/2569788/detail_2569788_10_500.jpg?t=20220530104204")
                    .build();

            ShopItem shopItem1 = ShopItem.builder()
                    .shop(lotteOnShop)
                    .itemImgUrl("https://contents.lotteon.com/itemimage/_v143540/LO/18/65/15/32/71/_1/86/51/53/27/2/LO1865153271_1865153272_1.png/dims/optimize/dims/resizemc/400x400")
                    .itemUrl("https://www.lotteon.com/m/product/LO1865153271?sitmNo=LO1865153271_1865153272&mall_no=1&dp_infw_cd=SCH%EA%B0%A4%EB%9F%AC%EB%A6%AC%EB%94%94%ED%8C%8C%ED%8A%B8%EB%A8%BC%ED%8A%B8")
                    .price(250000L)
                    .soldOutStatus(SoldOutStatus.N)
                    .build();

            ShopItem shopItem2 = ShopItem.builder()
                    .shop(trenbeShop)
                    .itemImgUrl("https://image-cdn.trenbe.com/productmain/1655965617308-11fdd4fc-163a-41f8-9351-56ec85cf1958.jpeg")
                    .itemUrl("https://www.trenbe.com/product/SOUVENIR+VST1000+32567796")
                    .price(286000L)
                    .soldOutStatus(SoldOutStatus.Y)
                    .build();

            ShopItem shopItem3 = ShopItem.builder()
                    .shop(mustitShop)
                    .itemImgUrl("https://cdn.mustit.co.kr/lib/upload/product/luxboy/2022/05/1653037065-72.jpeg/_dims_/resize/500x500/extent/500x500")
                    .itemUrl("https://m.web.mustit.co.kr/m/product/product_detail/42280054")
                    .price(116200L)
                    .soldOutStatus(SoldOutStatus.N)
                    .build();

            ShopItem shopItem4 = ShopItem.builder()
                    .shop(musinsaShop)
                    .itemImgUrl("https://image.msscdn.net/images/prd_img/20220519/2569788/detail_2569788_10_500.jpg?t=20220530104204")
                    .itemUrl("https://www.musinsa.com/app/goods/2569788")
                    .price(57780L)
                    .soldOutStatus(SoldOutStatus.N)
                    .build();


            item1.addShopItem(shopItem1);
            item1.addShopItem(shopItem2);

            item2.addShopItem(shopItem3);
            item3.addShopItem(shopItem4);

            em.persist(item1);
            em.persist(item2);
            em.persist(item3);


            TimeFrame timeFrame1 = TimeFrame.builder()
                    .startTime(191L)
                    .endTime(196L)
                    .model(model)
                    .build();
            timeFrame1.addItem(item1);
            timeFrame1.addItem(item2);

            em.persist(timeFrame1);

            TimeFrame timeFrame2 = TimeFrame.builder()
                    .startTime(334L)
                    .endTime(337L)
                    .model(model)
                    .build();

            timeFrame2.addItem(item3);

            em.persist(timeFrame2);

            List<TimeFrame> timeFrameList = new ArrayList<>();
            timeFrameList.add(timeFrame1);
            timeFrameList.add(timeFrame2);

            Channel channel = Channel.builder()
                    .name("핏더사이즈")
                    .url("https://www.youtube.com/c/%ED%95%8F%EB%8D%94%EC%82%AC%EC%9D%B4%EC%A6%88/about").build();

            YoutubeCreator youtubeCreator = YoutubeCreator.builder()
                    .creatorName("핏더사이즈")
                    .profileImgUrl("https://yt3.ggpht.com/ytc/AMLnZu-opBs4fsY6Vw06U1VMoTpcaepRcYtkmmPSuOl1=s176-c-k-c0x00ffffff-no-rj")
                    .build();


            Video video = Video.builder().videoUrl("i1iAY4brK-s")
                   .length(696L)
                   .title("여름 가성비 데님팬츠 맛집 찾았다☀")
                   .thumbnailUrl("https://i.ytimg.com/an_webp/i1iAY4brK-s/mqdefault_6s.webp?du=3000&sqp=CO2jrJcG&rs=AOn4CLAHALx7ZUVWi-aSbqe3Qdup7hU7rg")
                   .channel(channel)
                   .youtubeCreator(youtubeCreator)
                    .timeFrames(timeFrameList).build();

            em.persist(channel);
            em.persist(youtubeCreator);
            em.persist(video);
        }
    }
}
