package com.clovi.app.video.service;

import com.clovi.app.base.dto.response.SavedId;
import com.clovi.app.channel.Channel;
import com.clovi.app.channel.ChannelRepository;
import com.clovi.exception.DuplicateResourceException;
import com.clovi.exception.ResourceNotFoundException;
import com.clovi.app.video.dto.request.VideoRequest;
import com.clovi.app.video.dto.response.VideoResponse;
import com.clovi.app.video.repository.VideoRepository;
import com.clovi.app.video.Video;

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

    public VideoResponse searchByVideoId(String videoId) {
        // NumberFormatException 처리 필요
        Optional<Video> video = videoRepository.findById(Long.parseLong(videoId));

        if(video.isEmpty()) {
            return null;
        }
        return new VideoResponse(video.get());
    }

    public SavedId searchByYoutubeVideoId(String youtubeVideoId) {
        Video video = videoRepository.findByYoutubeVideoId(youtubeVideoId)
                .orElseThrow(() -> new ResourceNotFoundException("video", youtubeVideoId));

        return new SavedId(video.getId());
    }

    /* Function deprecated
    public VideoResponse search(String videoUrl) {
        Optional<Video> video = videoRepository.findByYoutubeVideoId(videoUrl);
        if(video.isPresent()) {
            VideoResponse result = new VideoResponse(video.get());
            return result;
        }
        return null;
    }
    */

    @Transactional
    public Long saveVideo(@NotNull VideoRequest videoRequest) {
        String channelId = videoRequest.getChannelId();
        Channel channel = channelRepository.findByChannelIdAndDeletedFalse(channelId)
                .orElseThrow(() -> new ResourceNotFoundException("channelId", channelId));

        Optional<Video> video = videoRepository.findByYoutubeVideoId(videoRequest.getYoutubeVideoId());
        if(video.isPresent()) {
            throw new DuplicateResourceException("video");
        }

        Video savedVideo = videoRepository.save(videoRequest.toEntity(channel));
        return savedVideo.getId();
    }
}
