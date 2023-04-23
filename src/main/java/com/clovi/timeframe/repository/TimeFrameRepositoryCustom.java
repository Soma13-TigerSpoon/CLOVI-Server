package com.clovi.timeframe.repository;

import com.clovi.timeframe.TimeFrame;

import java.util.List;

public interface TimeFrameRepositoryCustom {

    List<TimeFrame> getTimeFramesByVideoIdAndItemInfoId(Long videoId, Long itemInfoId);
}
