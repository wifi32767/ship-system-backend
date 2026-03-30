package com.wifi32767.infra.adapter.repository;

import com.wifi32767.domain.portal.adapter.repository.PortalRepository;
import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.domain.portal.model.NewsVO;
import com.wifi32767.infra.dao.DeviceDao;
import com.wifi32767.infra.dao.po.Device;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PortalRepositoryImp implements PortalRepository {

    @Resource
    private DeviceDao deviceDao;

    @Override
    public int queryDeviceCount() {
        return deviceDao.queryAllCount();
    }

    @Override
    public int queryDeviceCountByDate(LocalDate date) {
        return deviceDao.queryCountByDate(date);
    }

    @Override
    public int queryDeviceCorrectionCountByDate(LocalDate date) {
        return deviceDao.queryCorrectionCountByDate(date);
    }

    @Override
    public List<NewsVO> queryLatestNews(int limit) {
        return LDevice2LNewsVO(deviceDao.queryLatestNews(limit));
    }

    @Override
    public List<DeviceVO> queryLatestDevices(int limit) {
        return LDevice2LDeviceVO(deviceDao.queryLatestDevices(limit));
    }

    @Override
    public List<DeviceVO> queryRandomDevices(int limit) {
        return LDevice2LDeviceVO(deviceDao.queryRandomDevices(limit));
    }

    private List<NewsVO> LDevice2LNewsVO(List<Device> deviceList) {
        List<NewsVO> newsList = new ArrayList<>();
        for (Device device : deviceList) {
            newsList.add(NewsVO.builder()
                    .title(device.getDeviceNewsTitle())
                    .introduce(device.getDeviceIntroduce())
                    .publishTime(device.getDeviceNewsTime())
                    .sourceLink(device.getDeviceNewsLink())
                    .img(device.getDeviceImg())
                    .video(device.getDeviceVideo())
                    .build()
            );
        }
        return newsList;
    }

    private List<DeviceVO> LDevice2LDeviceVO(List<Device> deviceList) {
        List<DeviceVO> deviceVOList = new ArrayList<>();
        for (Device device : deviceList) {
            deviceVOList.add(DeviceVO.builder()
                    .id(device.getDeviceId())
                    .deviceName(device.getDeviceName())
                    .deviceClassId(device.getDeviceClassId())
                    .deviceStyleId(device.getDeviceStyleId())
                    .deviceTypeId(device.getDeviceTypeId())
                    .deviceUseYear(device.getDeviceUseYear())
                    .devicePrice(device.getDevicePrice())
                    .deviceUsingUnit(device.getDeviceUsingUnit())
                    .deviceCountryId(device.getDeviceCountryId())
                    .deviceLocation(device.getDeviceLocation())
                    .deviceLongitude(device.getDeviceLongitude())
                    .deviceLatitude(device.getDeviceLatitude())
                    .deviceImg(device.getDeviceImg())
                    .deviceVideo(device.getDeviceVideo())
                    .deviceIntroduce(device.getDeviceIntroduce())
                    .deviceInsqlTime(device.getDeviceInsqlTime())
                    .deviceChangesqlTime(device.getDeviceChangesqlTime())
                    .build()
            );
        }
        return deviceVOList;
    }
}
