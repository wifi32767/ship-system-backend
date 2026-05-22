package com.wifi32767.infra.adapter.repository;

import com.wifi32767.domain.portal.adapter.repository.PortalRepository;
import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.domain.portal.model.NewsVO;
import com.wifi32767.infra.dao.DeviceDao;
import com.wifi32767.infra.dao.po.Device;
import com.wifi32767.infra.redis.RedisService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PortalRepositoryImp implements PortalRepository {

    @Resource
    private RedisService redisService;

    @Resource
    private DeviceDao deviceDao;

    // TODO: 我觉得可以提取公共缓存方法
    @Override
    public int queryDeviceCount() {
        Integer cnt = redisService.getValue(DeviceCache.DEVICE_COUNT_TOTAL);
        if (cnt != null) {
            return cnt;
        }
        cnt = deviceDao.queryAllCount();
        redisService.setValue(DeviceCache.DEVICE_COUNT_TOTAL, cnt);
        return cnt;
    }

    @Override
    public int queryDeviceCountByDate(LocalDate date) {
        Integer cnt = redisService.getValue(DeviceCache.DEVICE_COUNT_BY_DATE + date);
        if (cnt != null) {
            return cnt;
        }
        cnt = deviceDao.queryCountByDate(date);
        redisService.setValue(DeviceCache.DEVICE_COUNT_BY_DATE + date, cnt);
        return cnt;
    }

    @Override
    public int queryDeviceCorrectionCountByDate(LocalDate date) {
        Integer cnt = redisService.getValue(DeviceCache.DEVICE_CORRECTION_COUNT_BY_DATE + date);
        if (cnt != null) {
            return cnt;
        }
        cnt = deviceDao.queryCorrectionCountByDate(date);
        redisService.setValue(DeviceCache.DEVICE_CORRECTION_COUNT_BY_DATE + date, cnt);
        return cnt;
    }

    @Override
    public List<NewsVO> queryLatestNews(int limit) {
        List<NewsVO> newsVOList = redisService.getValue(DeviceCache.LATEST_NEWS_LIMIT + limit);
        if (newsVOList != null) {
            return newsVOList;
        }
        newsVOList = LDevice2LNewsVO(deviceDao.queryLatestNews(limit));
        redisService.setValue(DeviceCache.LATEST_NEWS_LIMIT + limit, newsVOList);
        return newsVOList;
    }

    @Override
    public List<DeviceVO> queryLatestDevices(int limit) {
        List<DeviceVO> deviceVOList = redisService.getValue(DeviceCache.LATEST_DEVICES_LIMIT + limit);
        if (deviceVOList != null) {
            return deviceVOList;
        }
        deviceVOList = LDevice2LDeviceVO(deviceDao.queryLatestDevices(limit));
        redisService.setValue(DeviceCache.LATEST_DEVICES_LIMIT + limit, deviceVOList);
        return deviceVOList;
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
