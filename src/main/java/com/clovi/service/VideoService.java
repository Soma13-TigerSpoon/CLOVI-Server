package com.clovi.service;

import com.clovi.domain.youtube.Channel;
import com.clovi.domain.youtube.Video;
import com.clovi.dto.requests.VideoRequestDto;
import com.clovi.dto.response.VideoResponseDto;
import com.clovi.repository.Video.VideoRepository;
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
