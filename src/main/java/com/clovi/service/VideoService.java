package com.clovi.service;

import com.clovi.domain.user.Member;
import com.clovi.domain.youtube.Channel;
import com.clovi.domain.youtube.Video;
import com.clovi.dto.requests.VideoRequestDto;
import com.clovi.dto.response.VideoResponseDto;
import com.clovi.exception.ResourceNotFoundException;
import com.clovi.exception.video.DuplicateVideoIdException;
import com.clovi.repository.ChannelRepository;
import com.clovi.repository.Video.VideoRepository;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.constraints.NotNull;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VideoService {

    private final VideoRepository videoRepository;
    private final ChannelRepository channelRepository;

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

    @Transactional
    public Long save(@NotNull VideoRequestDto videoRequestDto, Channel channel) {
        Video video = videoRepository.findByYoutubeVideoId(videoRequestDto.getYoutubeVideoId()).orElse(
            new Video(videoRequestDto, channel)
        );
        videoRepository.save(video);
        return video.getId();
    }

    @Transactional
    public Long save(@NotNull VideoRequestDto videoRequestDto) {
        String channelUrl = videoRequestDto.getChannelUrl();
        Channel channel = channelRepository.findByChannelUrl(channelUrl).orElseThrow(
                () -> new ResourceNotFoundException("channelUrl", channelUrl)
        );

        String videoId = videoRequestDto.getYoutubeVideoId();
        Optional<Video> video = videoRepository.findByYoutubeVideoId(videoId);
        if(video.isPresent()) throw new DuplicateVideoIdException();

        Video newVideo = new Video(videoRequestDto, channel);
        Video savedVideo = videoRepository.save(newVideo);
        return savedVideo.getId();
    }
}
