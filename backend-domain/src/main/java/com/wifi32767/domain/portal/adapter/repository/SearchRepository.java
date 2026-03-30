package com.wifi32767.domain.portal.adapter.repository;

import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.domain.portal.model.NewsVO;

import java.util.List;

public interface SearchRepository {
    List<DeviceVO> searchDeviceByText(String param);

    List<DeviceVO> searchDeviceByTitle(String param);

    List<DeviceVO> searchDeviceByType(int param);

    List<DeviceVO> searchDeviceByStyle(int param);

    List<DeviceVO> searchDeviceByClass(int param);

    List<DeviceVO> searchDeviceByCountry(String param);

    List<NewsVO> searchNewsByText(String param);

    List<NewsVO> searchNewsByTitle(String param);

    List<NewsVO> searchNewsByType(int param);

    List<NewsVO> searchNewsByStyle(int param);

    List<NewsVO> searchNewsByClass(int param);

    List<NewsVO> searchNewsByCountry(String param);
}
