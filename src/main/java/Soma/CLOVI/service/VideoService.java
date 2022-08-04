package Soma.CLOVI.service;

import Soma.CLOVI.domain.ShopItem;
import Soma.CLOVI.domain.TimeFrame;
import Soma.CLOVI.domain.user.YoutubeCreator;
import Soma.CLOVI.domain.youtube.Channel;
import Soma.CLOVI.domain.youtube.Video;
import Soma.CLOVI.dto.use.AllDto;
import Soma.CLOVI.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;


    public AllDto makeAllDto(String videoUrl){
        Optional<Video> video = videoRepository.findByVideoUrl(videoUrl);
        if(video.isPresent()){
            AllDto allDto = new AllDto(video.get());

            return allDto;
        }
        return new AllDto();
    }
}
