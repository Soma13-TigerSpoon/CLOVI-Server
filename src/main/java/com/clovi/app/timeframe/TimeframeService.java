package com.clovi.app.timeframe;

import com.clovi.exception.DuplicateResourceException;
import com.clovi.exception.ResourceNotFoundException;
import com.clovi.exception.auth.NoPermissionDeleteException;
import com.clovi.exception.auth.NoPermissionUpdateException;
import com.clovi.app.member.Member;
import com.clovi.app.timeShopItem.dto.response.TimeShopItemResponse;
import com.clovi.app.timeframe.dto.request.TimeframeCreateRequest;
import com.clovi.app.timeframe.dto.request.TimeframeUpdateRequest;
import com.clovi.app.timeframe.dto.response.TimeframeResponse;
import com.clovi.app.timeframe.repository.TimeframeRepository;
import com.clovi.app.video.Video;
import com.clovi.app.video.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TimeframeService {

    private final TimeframeRepository timeFrameRepository;
    private final VideoRepository videoRepository;

    public List<TimeframeResponse> getTimeFrameListByYoutubeVideoId(String youtubeVideoId) {
        Video video = videoRepository.findByYoutubeVideoIdAndDeletedIsFalse(youtubeVideoId).orElseThrow(() -> new ResourceNotFoundException("video",youtubeVideoId));
        List<Timeframe> timeframes = timeFrameRepository.findAllByVideoIdAndDeletedIsFalse(video.getId());
        List<TimeframeResponse> result = timeframes.stream().map(TimeframeResponse::new).collect(Collectors.toList());
        if(!result.isEmpty()) {
            result.sort(Comparator.comparing(TimeframeResponse::getStart));
        }
        return result;
    }

    public TimeShopItemResponse getItemListByTimeFrameId(Long timeFrameId) {
        Timeframe timeFrame = timeFrameRepository.findByIdAndDeletedIsFalse(timeFrameId).orElseThrow(() -> new ResourceNotFoundException("timeFrame",timeFrameId));
        TimeShopItemResponse result = new TimeShopItemResponse(timeFrame);
        return result;
    }

    @Transactional
    public Long create(TimeframeCreateRequest timeFrameCreateRequest, String youtubeVideoId, Member member) {
        Video video = videoRepository.findByYoutubeVideoIdAndDeletedIsFalse(youtubeVideoId).orElseThrow(() -> new ResourceNotFoundException("video",youtubeVideoId));
        Long capturePoint = timeFrameCreateRequest.getTime();
        if(timeFrameRepository.existsByVideoIdAndCapturePointAndDeletedIsFalse(video.getId(),capturePoint)){
            throw new DuplicateResourceException("timeFrame");
        }
//        channel 관련 권한 설정 논의
//        if(member.getChannel().getId() != video.getChannel().getId()){
//            throw new NoPermissionCreateException();
//        }
        Timeframe timeFrame = new Timeframe(timeFrameCreateRequest,video,member.getId());
        Timeframe saved = timeFrameRepository.save(timeFrame);
        return saved.getId();
    }

    @Transactional
    public Long update(TimeframeUpdateRequest timeFrameUpdateRequest, Long timeFrameId, Member member) {
        Timeframe timeFrame = timeFrameRepository.findByIdAndDeletedIsFalse(timeFrameId).orElseThrow(() -> new ResourceNotFoundException("timeFrame",timeFrameId));
        Long memberId = member.getId();
        if(timeFrame.isNotCreatedBy(memberId)){ //수정 권한은 생성한 사람만 가지고 있음
            throw new NoPermissionUpdateException();
        }
        timeFrame.update(timeFrameUpdateRequest, member.getId());
        Timeframe updated = timeFrameRepository.save(timeFrame);
        return updated.getId();
    }


    @Transactional
    public void delete(Long timeFrameId, Member member) {
        Timeframe timeFrame = timeFrameRepository.findByIdAndDeletedIsFalse(timeFrameId).orElseThrow(() -> new ResourceNotFoundException("timeFrame",timeFrameId));
        if(timeFrame.isNotCreatedBy(member.getId())){//삭제 권한은 생성한 사람만 가지고 있음
            throw new NoPermissionDeleteException();
        }
        timeFrame.delete();
        timeFrameRepository.save(timeFrame);

    }
}
