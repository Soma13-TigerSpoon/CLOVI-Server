package com.clovi.app.timeframe.repository;

import com.clovi.app.timeframe.Timeframe;

import java.util.List;

public interface TimeframeRepositoryCustom {

    List<Timeframe> getTimeFramesByVideoIdAndItemInfoId(Long videoId, Long itemInfoId);
}
