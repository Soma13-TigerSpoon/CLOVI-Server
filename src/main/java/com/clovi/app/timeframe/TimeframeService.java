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
    private final TimeframeRepository timeframeRepository;
    private final VideoRepository videoRepository;

    /* Function deprecated
    public List<TimeframeResponse> getTimeFrameListByYoutubeVideoId(String youtubeVideoId) {
        Video video = videoRepository.findByYoutubeVideoIdAndDeletedIsFalse(youtubeVideoId).orElseThrow(() -> new ResourceNotFoundException("video",youtubeVideoId));
        List<Timeframe> timeframes = timeFrameRepository.findAllByVideoIdAndDeletedIsFalse(video.getId());
        List<TimeframeResponse> result = timeframes.stream().map(TimeframeResponse::new).collect(Collectors.toList());
        if(!result.isEmpty()) {
            result.sort(Comparator.comparing(TimeframeResponse::getStart));
        }
        return result;
    }
    */

    public List<TimeframeResponse> getTimeframeListByVideoId(String videoId) {
        Video video = videoRepository.findByIdAndDeletedIsFalse(Long.parseLong(videoId))
                .orElseThrow(() -> new ResourceNotFoundException("video", videoId));

        List<Timeframe> timeframes = timeframeRepository.findAllByVideoIdAndDeletedIsFalse(video.getId());

        Comparator<TimeframeResponse> compareCapturePoint = Comparator.comparing(TimeframeResponse::getStart);

        return timeframes
                .stream()
                .map(TimeframeResponse::new)
                .sorted(compareCapturePoint)
                .collect(Collectors.toList());
    }

    /* Function deprecated
    public TimeShopItemResponse getItemListByTimeFrameId(Long timeFrameId) {
        Timeframe timeFrame = timeFrameRepository.findByIdAndDeletedIsFalse(timeFrameId).orElseThrow(() -> new ResourceNotFoundException("timeFrame",timeFrameId));
        TimeShopItemResponse result = new TimeShopItemResponse(timeFrame);
        return result;
    }
    */

    public TimeShopItemResponse getItemListByVideoIdAndTimeframeId(String videoId, Long timeframeId) {
        Timeframe timeframe = checkIfVideoAndTimeframeExists(videoId, timeframeId);

        return new TimeShopItemResponse(timeframe);
    }

    /* Function deprecated
    @Transactional
    public Long create(TimeframeCreateRequest timeframeCreateRequest, String youtubeVideoId, Member member) {
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
    */

    @Transactional
    public Long createTimeframe(TimeframeCreateRequest timeframeCreateRequest, String videoId, Member member) {
        Video video = videoRepository.findByIdAndDeletedIsFalse(Long.parseLong(videoId))
                .orElseThrow(() -> new ResourceNotFoundException("video", videoId));

        if(timeframeRepository.existsByVideoIdAndCapturePointAndDeletedIsFalse(video.getId(), timeframeCreateRequest.getTime())) {
            throw new DuplicateResourceException("timeframe");
        }

        Timeframe saved = timeframeRepository.save(
                new Timeframe(timeframeCreateRequest, video, member.getId())
        );
        return saved.getId();
    }

    /* Function deprecated
    @Transactional
    public Long update(TimeframeUpdateRequest timeframeUpdateRequest, Long timeFrameId, Member member) {
        Timeframe timeFrame = timeFrameRepository.findByIdAndDeletedIsFalse(timeFrameId).orElseThrow(() -> new ResourceNotFoundException("timeFrame",timeFrameId));
        Long memberId = member.getId();
        if(timeFrame.isNotCreatedBy(memberId)){ //수정 권한은 생성한 사람만 가지고 있음
            throw new NoPermissionUpdateException();
        }
        timeFrame.update(timeFrameUpdateRequest, member.getId());
        Timeframe updated = timeFrameRepository.save(timeFrame);
        return updated.getId();
    }
    */

    @Transactional
    public Long updateTimeframe(TimeframeUpdateRequest timeframeUpdateRequest, String videoId, Long timeframeId, Member member) {
        Timeframe timeframe = checkIfVideoAndTimeframeExists(videoId, timeframeId);

        // 수정 권한은 생성한 사람만 가지고 있음
        if(timeframe.isNotCreatedBy(member.getId())) {
            throw new NoPermissionUpdateException();
        }

        timeframe.update(timeframeUpdateRequest, member.getId());
        Timeframe updated = timeframeRepository.save(timeframe);
        return updated.getId();
    }

    /*
    @Transactional
    public void delete(Long timeFrameId, Member member) {
        Timeframe timeFrame = timeFrameRepository.findByIdAndDeletedIsFalse(timeFrameId).orElseThrow(() -> new ResourceNotFoundException("timeFrame",timeFrameId));
        if(timeFrame.isNotCreatedBy(member.getId())){//삭제 권한은 생성한 사람만 가지고 있음
            throw new NoPermissionDeleteException();
        }
        timeFrame.delete();
        timeFrameRepository.save(timeFrame);
    }
    */

    @Transactional
    public void deleteTimeframe(String videoId, Long timeframeId, Member member) {
        Timeframe timeframe = checkIfVideoAndTimeframeExists(videoId, timeframeId);

        // 삭제 권한은 생성한 사람만 가지고 있음
        if(timeframe.isNotCreatedBy(member.getId())) {
            throw new NoPermissionDeleteException();
        }

        timeframe.delete();
        timeframeRepository.save(timeframe);
    }

    private Timeframe checkIfVideoAndTimeframeExists(String videoId, Long timeframeId) {
        if(videoRepository.findByIdAndDeletedIsFalse(Long.parseLong(videoId)).isEmpty()) {
            throw new ResourceNotFoundException("video", videoId);
        }

        return timeframeRepository.findByIdAndVideoIdAndDeletedIsFalse(timeframeId, Long.parseLong(videoId))
                .orElseThrow(() -> new ResourceNotFoundException("timeframe", timeframeId));
    }
}
