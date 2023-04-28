package com.clovi.app.video.service;

import com.clovi.app.channel.domain.Channel;
import com.clovi.app.video.domain.Video;
import com.clovi.app.video.dto.request.VideoRequest;
import com.clovi.app.video.dto.response.VideoResponse;
import com.clovi.exception.ResourceNotFoundException;
import com.clovi.exception.video.DuplicateVideoIdException;
import com.clovi.app.channel.repository.ChannelRepository;
import com.clovi.app.video.repository.VideoRepository;

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

    public VideoResponse search(String videoUrl){
        Optional<Video> video = videoRepository.findByYoutubeVideoId(videoUrl);
        if(video.isPresent()){
            VideoResponse result = new VideoResponse(video.get());
            return result;
        }
        return null;
//        Video video = videoRepository.findByVideoUrl(videoUrl).orElseThrow(
//            ()->  new RuntimeException("")
//        );
//        return new VideoResponse(video);
    }

    @Transactional
    public Long save(@NotNull VideoRequest videoRequest, Channel channel) {
        Video video = videoRepository.findByYoutubeVideoId(videoRequest.getYoutubeVideoId()).orElse(
            new Video(videoRequest, channel)
        );
        videoRepository.save(video);
        return video.getId();
    }

    @Transactional
    public Long save(@NotNull VideoRequest videoRequest) {
        String channelUrl = videoRequest.getChannelUrl();
        Channel channel = channelRepository.findByChannelUrl(channelUrl).orElseThrow(
                () -> new ResourceNotFoundException("channelUrl", channelUrl)
        );

        String videoId = videoRequest.getYoutubeVideoId();
        Optional<Video> video = videoRepository.findByYoutubeVideoId(videoId);
        if(video.isPresent()) throw new DuplicateVideoIdException();

        Video newVideo = new Video(videoRequest, channel);
        Video savedVideo = videoRepository.save(newVideo);
        return savedVideo.getId();
    }
}
