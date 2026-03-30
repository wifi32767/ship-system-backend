package com.wifi32767.domain.portal.adapter.repository;

import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.domain.portal.model.NewsVO;

import java.time.LocalDate;
import java.util.List;

public interface PortalRepository {
    int queryDeviceCount();
    int queryDeviceCountByDate(LocalDate date);
    int queryDeviceCorrectionCountByDate(LocalDate date);
    List<NewsVO> queryLatestNews(int limit);
    List<DeviceVO> queryLatestDevices(int limit);
    List<DeviceVO> queryRandomDevices(int limit);
}
