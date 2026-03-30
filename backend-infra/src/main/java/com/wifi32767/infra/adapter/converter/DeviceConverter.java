package com.wifi32767.infra.adapter.converter;

import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.domain.portal.model.NewsVO;
import com.wifi32767.infra.dao.po.Device;

import java.util.ArrayList;
import java.util.List;

public class DeviceConverter {
    static public List<NewsVO> LDevice2LNewsVO(List<Device> deviceList) {
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

    static public List<DeviceVO> LDevice2LDeviceVO(List<Device> deviceList) {
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
