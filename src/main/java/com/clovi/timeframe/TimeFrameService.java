package com.clovi.timeframe;

import com.clovi.exception.DuplicateResourceException;
import com.clovi.exception.ResourceNotFoundException;
import com.clovi.exception.auth.NoPermissionDeleteException;
import com.clovi.exception.auth.NoPermissionUpdateException;
import com.clovi.member.Member;
import com.clovi.timeShopItem.dto.response.TimeShopItemResponseDto;
import com.clovi.timeframe.dto.request.TimeFrameCreateRequest;
import com.clovi.timeframe.dto.request.TimeFrameUpdateRequest;
import com.clovi.timeframe.dto.response.TimeFrameResponseDto;
import com.clovi.timeframe.repository.TimeFrameRepository;
import com.clovi.video.Video;
import com.clovi.video.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TimeFrameService {

    private final TimeFrameRepository timeFrameRepository;
    private final VideoRepository videoRepository;

    public List<TimeFrameResponseDto> getTimeFrameListByYoutubeVideoId(String youtubeVideoId) {
        Video video = videoRepository.findByYoutubeVideoIdAndDeletedIsFalse(youtubeVideoId).orElseThrow(() -> new ResourceNotFoundException("video",youtubeVideoId));
        List<TimeFrame> timeFrames = timeFrameRepository.findAllByVideoIdAndDeletedIsFalse(video.getId());
        List<TimeFrameResponseDto> result = timeFrames.stream().map(TimeFrameResponseDto::new).collect(Collectors.toList());
        if(!result.isEmpty()) {
            result.sort(Comparator.comparing(TimeFrameResponseDto::getStart));
        }
        return result;
    }

    public TimeShopItemResponseDto getItemListByTimeFrameId(Long timeFrameId) {
        TimeFrame timeFrame = timeFrameRepository.findByIdAndDeletedIsFalse(timeFrameId).orElseThrow(() -> new ResourceNotFoundException("timeFrame",timeFrameId));
        TimeShopItemResponseDto result = new TimeShopItemResponseDto(timeFrame);
        return result;
    }

    @Transactional
    public Long create(TimeFrameCreateRequest timeFrameCreateRequest, String youtubeVideoId, Member member) {
        Video video = videoRepository.findByYoutubeVideoIdAndDeletedIsFalse(youtubeVideoId).orElseThrow(() -> new ResourceNotFoundException("video",youtubeVideoId));
        Long capturePoint = timeFrameCreateRequest.getTime();
        if(timeFrameRepository.existsByVideoIdAndCapturePointAndDeletedIsFalse(video.getId(),capturePoint)){
            throw new DuplicateResourceException("timeFrame");
        }
//        channel 관련 권한 설정 논의
//        if(member.getChannel().getId() != video.getChannel().getId()){
//            throw new NoPermissionCreateException();
//        }
        TimeFrame timeFrame = new TimeFrame(timeFrameCreateRequest,video,member.getId());
        TimeFrame saved = timeFrameRepository.save(timeFrame);
        return saved.getId();
    }

    @Transactional
    public Long update(TimeFrameUpdateRequest timeFrameUpdateRequest, Long timeFrameId, Member member) {
        TimeFrame timeFrame = timeFrameRepository.findByIdAndDeletedIsFalse(timeFrameId).orElseThrow(() -> new ResourceNotFoundException("timeFrame",timeFrameId));
        Long memberId = member.getId();
        if(timeFrame.isNotCreatedBy(memberId)){ //수정 권한은 생성한 사람만 가지고 있음
            throw new NoPermissionUpdateException();
        }
        timeFrame.update(timeFrameUpdateRequest, member.getId());
        TimeFrame updated = timeFrameRepository.save(timeFrame);
        return updated.getId();
    }


    @Transactional
    public void delete(Long timeFrameId, Member member) {
        TimeFrame timeFrame = timeFrameRepository.findByIdAndDeletedIsFalse(timeFrameId).orElseThrow(() -> new ResourceNotFoundException("timeFrame",timeFrameId));
        if(timeFrame.isNotCreatedBy(member.getId())){//삭제 권한은 생성한 사람만 가지고 있음
            throw new NoPermissionDeleteException();
        }
        timeFrame.delete();
        timeFrameRepository.save(timeFrame);

    }
}
