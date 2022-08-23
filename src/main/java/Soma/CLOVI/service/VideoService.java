package Soma.CLOVI.service;

import Soma.CLOVI.domain.user.YoutubeCreator;
import Soma.CLOVI.domain.youtube.Channel;
import Soma.CLOVI.domain.youtube.Video;
import Soma.CLOVI.dto.use.VideoRequestDto;
import Soma.CLOVI.dto.use.VideoResponseDto;
import Soma.CLOVI.repository.ChannelRepository;
import Soma.CLOVI.repository.video.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;

    private final ChannelRepository channelRepository;


    public VideoResponseDto search(String videoUrl){
        Optional<Video> video = videoRepository.findByVideoUrl(videoUrl);
        if(video.isPresent()){
            VideoResponseDto result = new VideoResponseDto(video.get());
            return result;
        }
        return null;
    }

    @Transactional
    public Long save(VideoRequestDto videoRequestDto) {
        Channel channel = isChannelExist(videoRequestDto.getChannelName(), videoRequestDto.getChannelUrl(), videoRequestDto.getChannelImg());
        Video video = videoRepository.findByVideoUrl(videoRequestDto.getVideoUrlId()).orElse(
                new Video(videoRequestDto, channel)

        );
        videoRepository.save(video);
        return video.getId();
    }
    public Channel isChannelExist(String channelName, String channelUrl, String profileImgUrl){
        return channelRepository.findByName(channelName).orElse(
                new Channel(channelName,channelUrl,profileImgUrl,new YoutubeCreator(channelName)) // 지금 유튜버는 채널명만 이름으로 가지고 있도록 함.
        );
    }
}
