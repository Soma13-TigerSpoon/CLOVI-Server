package Soma.CLOVI;

import Soma.CLOVI.domain.Gender;
import Soma.CLOVI.domain.ManyToMany.TimeItem;
import Soma.CLOVI.domain.ManyToMany.VideoItem;
import Soma.CLOVI.domain.Model;
import Soma.CLOVI.domain.ManyToMany.ShopItem;
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


            Model model = new Model("핏더사이즈",88.0f,183.0f,Gender.M);

            em.persist(model);


            Shop lotteOnShop = new Shop("lotteOn", "https://ko.m.wikipedia.org/wiki/%ED%8C%8C%EC%9D%BC:Lotte_%22Value_Line%22_logo.svg");
            Shop trenbeShop = new Shop("trenbe","https://www.techm.kr/news/thumbnail/202008/74445_67428_1335_v150.jpg");
            Shop mustitShop = new Shop("mustit","https://s3.ap-northeast-2.amazonaws.com/mustit-ux/img/m/m_common/ban_share_common.png");
            Shop musinsaShop = new Shop("musinsa","https://pds.saramin.co.kr/company/logo/202202/21/r7n4lg_iaut-i637x_logo.JPG");

            em.persist(lotteOnShop);
            em.persist(trenbeShop);
            em.persist(musinsaShop);
            em.persist(mustitShop);


            YoutubeCreator youtubeCreator =  new YoutubeCreator("핏더사이즈");

            Channel channel = new Channel("핏더사이즈","https://www.youtube.com/c/%ED%95%8F%EB%8D%94%EC%82%AC%EC%9D%B4%EC%A6%88/about" ,"https://yt3.ggpht.com/ytc/AMLnZu-opBs4fsY6Vw06U1VMoTpcaepRcYtkmmPSuOl1=s176-c-k-c0x00ffffff-no-rj",youtubeCreator);

            Video video = new Video("여름 가성비 데님팬츠 맛집 찾았다☀",
                    "i1iAY4brK-s",
                    696L,
                    youtubeCreator,
                    channel);



            TimeFrame timeFrame1 = TimeFrame.builder()
                    .capturePoint(191L)
                    .model(model)
                    .video(video)
                    .build();

            //em.persist(timeFrame1);

            TimeFrame timeFrame2 = TimeFrame.builder()
                    .capturePoint(334L)
                    .model(model)
                    .video(video)
                    .build();



            Item item1 = Item.builder()
                    .name("갤러리 디파트먼트 Vintage Souvenir 반팔티")
                    .color("Black")
                    .size("XXL")
                    .itemType(ItemType.TOP)
                    .imgUrl("https://www.lotteon.com/m/product/LO1865153271?sitmNo=LO1865153271_1865153272&mall_no=1&dp_infw_cd=SCH%EA%B0%A4%EB%9F%AC%EB%A6%AC%EB%94%94%ED%8C%8C%ED%8A%B8%EB%A8%BC%ED%8A%B8")
                    .build();

            Item item2 = Item.builder()
                    .name("나누슈카 로고 볼캡")
                    .color("Black")
                    .size("free")
                    .itemType(ItemType.CAP)
                    .imgUrl("https://cdn.mustit.co.kr/lib/upload/product/luxboy/2022/05/1653037065-72.jpeg/_dims_/resize/500x500/extent/500x500")
                    .build();

            Item item3 = Item.builder()
                    .name("페이탈리즘 #0280 mer two tuck wide indigo")
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
                    .item(item1)
                    .soldOutStatus(SoldOutStatus.N)
                    .build();

            ShopItem shopItem2 = ShopItem.builder()
                    .shop(trenbeShop)
                    .itemImgUrl("https://image-cdn.trenbe.com/productmain/1655965617308-11fdd4fc-163a-41f8-9351-56ec85cf1958.jpeg")
                    .itemUrl("https://www.trenbe.com/product/SOUVENIR+VST1000+32567796")
                    .price(286000L)
                    .item(item1)
                    .soldOutStatus(SoldOutStatus.Y)
                    .build();

            ShopItem shopItem3 = ShopItem.builder()
                    .shop(mustitShop)
                    .itemImgUrl("https://cdn.mustit.co.kr/lib/upload/product/luxboy/2022/05/1653037065-72.jpeg/_dims_/resize/500x500/extent/500x500")
                    .itemUrl("https://m.web.mustit.co.kr/m/product/product_detail/42280054")
                    .price(116200L)
                    .item(item2)
                    .soldOutStatus(SoldOutStatus.N)
                    .build();

            ShopItem shopItem4 = ShopItem.builder()
                    .shop(musinsaShop)
                    .itemImgUrl("https://image.msscdn.net/images/prd_img/20220519/2569788/detail_2569788_10_500.jpg?t=20220530104204")
                    .itemUrl("https://www.musinsa.com/app/goods/2569788")
                    .price(57780L)
                    .item(item3)
                    .soldOutStatus(SoldOutStatus.N)
                    .build();

            VideoItem videoItem1 = new VideoItem(video,item1);
            VideoItem videoItem2 = new VideoItem(video,item2);
            VideoItem videoItem3 = new VideoItem(video,item3);


            item1.addShopItem(shopItem1);
            item1.addShopItem(shopItem2);
            item2.addShopItem(shopItem3);
            item3.addShopItem(shopItem4);

            TimeItem timeItem1 = new TimeItem(timeFrame1,item1);
            TimeItem timeItem2 = new TimeItem(timeFrame2,item2);
            TimeItem timeItem3 = new TimeItem(timeFrame2,item3);
            em.persist(item1);
            em.persist(item2);
            em.persist(item3);
            em.flush();

            timeFrame1.addItem(timeItem1);
            timeFrame2.addItem(timeItem2);
            timeFrame2.addItem(timeItem3);


            video.addVideoItem(videoItem1);
            video.addVideoItem(videoItem2);
            video.addVideoItem(videoItem3);

            video.addTimeFrame(timeFrame1);
            video.addTimeFrame(timeFrame2);

            youtubeCreator.addChannel(channel);

            channel.addVideo(video);


            em.persist(youtubeCreator);
        }
    }
}
