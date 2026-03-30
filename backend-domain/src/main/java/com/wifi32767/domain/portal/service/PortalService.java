package com.wifi32767.domain.portal.service;

import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.domain.portal.model.NewsVO;
import com.wifi32767.domain.portal.model.StatsBoardVO;

import java.util.List;

public interface PortalService {
    StatsBoardVO getStatsBoard();

    List<NewsVO> getLatestNews(int count);

    List<DeviceVO> getLatestDevices(int count);

    List<DeviceVO> getRandomDevices(int count);
}
