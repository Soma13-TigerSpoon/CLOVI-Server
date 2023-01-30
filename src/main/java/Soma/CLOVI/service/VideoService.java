package Soma.CLOVI.service;

import Soma.CLOVI.domain.youtube.Channel;
import Soma.CLOVI.domain.youtube.Video;
import Soma.CLOVI.dto.requests.VideoRequestDto;
import Soma.CLOVI.dto.response.VideoResponseDto;
import Soma.CLOVI.repository.ChannelRepository;
import Soma.CLOVI.repository.Video.VideoRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;


    public VideoResponseDto search(String videoUrl){
        Optional<Video> video = videoRepository.findByYoutubeVideoId(videoUrl);
        if(video.isPresent()){
            VideoResponseDto result = new VideoResponseDto(video.get());
            return result;
        }
        return null;
//        Video video = videoRepository.findByVideoUrl(videoUrl).orElseThrow(
//            ()->  new RuntimeException("")
//        );
//        return new VideoResponseDto(video);
    }

    public Long save(VideoRequestDto videoRequestDto, Channel channel){
        Video video = videoRepository.findByYoutubeVideoId(videoRequestDto.getYoutubeVideoId()).orElse(
            new Video(videoRequestDto, channel)
        );
        videoRepository.save(video);
        return video.getId();
    }
}
