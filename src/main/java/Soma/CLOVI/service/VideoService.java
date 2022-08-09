package Soma.CLOVI.service;

import Soma.CLOVI.domain.youtube.Video;
import Soma.CLOVI.dto.use.VideoResponseDto;
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


    public VideoResponseDto search(String videoUrl){
        Optional<Video> video = videoRepository.findByVideoUrl(videoUrl);
        if(video.isPresent()){
            VideoResponseDto result = new VideoResponseDto(video.get());

            return result;
        }
        return null;
    }
}
