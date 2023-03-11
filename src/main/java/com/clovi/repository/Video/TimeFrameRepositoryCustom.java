package com.clovi.repository.Video;

import com.clovi.domain.Model;
import com.clovi.domain.TimeFrame;

import java.util.List;

public interface TimeFrameRepositoryCustom {

    List<TimeFrame> getTimeFramesByVideoIdAndItemInfoId(Long videoId, Long itemInfoId);
}
