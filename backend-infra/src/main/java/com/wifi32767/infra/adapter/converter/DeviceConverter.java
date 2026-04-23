package com.wifi32767.infra.adapter.converter;

import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.domain.portal.model.NewsVO;
import com.wifi32767.domain.system.adapter.repository.ClassRepository;
import com.wifi32767.domain.system.adapter.repository.CountryRepository;
import com.wifi32767.domain.system.model.FullDeviceVO;
import com.wifi32767.infra.dao.po.Device;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class DeviceConverter {
    @Resource
    private ClassRepository classRepository;

    @Resource
    private CountryRepository countryRepository;

    public DeviceVO Device2DeviceVO(Device device) {
        return DeviceVO.builder()
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
                .auditFlag(device.getAuditFlag())
                .build();
    }

    public Device DeviceVO2Device(DeviceVO deviceVO) {
        return Device.builder()
                .deviceId(deviceVO.getId())
                .deviceName(deviceVO.getDeviceName())
                .deviceClassId(deviceVO.getDeviceClassId())
                .deviceStyleId(deviceVO.getDeviceStyleId())
                .deviceTypeId(deviceVO.getDeviceTypeId())
                .deviceUseYear(deviceVO.getDeviceUseYear())
                .devicePrice(deviceVO.getDevicePrice())
                .deviceUsingUnit(deviceVO.getDeviceUsingUnit())
                .deviceCountryId(deviceVO.getDeviceCountryId())
                .deviceLocation(deviceVO.getDeviceLocation())
                .deviceLongitude(deviceVO.getDeviceLongitude())
                .deviceLatitude(deviceVO.getDeviceLatitude())
                .deviceImg(deviceVO.getDeviceImg())
                .deviceVideo(deviceVO.getDeviceVideo())
                .deviceIntroduce(deviceVO.getDeviceIntroduce())
                .deviceInsqlTime(deviceVO.getDeviceInsqlTime())
                .deviceChangesqlTime(deviceVO.getDeviceChangesqlTime())
                .auditFlag(deviceVO.getAuditFlag())
                .build();
    }

    public FullDeviceVO Device2FDeviceVO(Device device) {
        return FullDeviceVO.builder()
                .id(device.getDeviceId())
                .deviceName(device.getDeviceName())
                .deviceClass(new FullDeviceVO.KeyValue(device.getDeviceClassId(), classRepository.getClassNameById(device.getDeviceClassId())))
                .deviceStyle(new FullDeviceVO.KeyValue(device.getDeviceStyleId(), classRepository.getStyleNameById(device.getDeviceStyleId())))
                .deviceType(new FullDeviceVO.KeyValue(device.getDeviceTypeId(), classRepository.getTypeNameById(device.getDeviceTypeId())))
                .deviceUseYear(device.getDeviceUseYear())
                .devicePrice(device.getDevicePrice())
                .deviceUsingUnit(device.getDeviceUsingUnit())
                .deviceCountry(new FullDeviceVO.KeyValue(device.getDeviceCountryId(), countryRepository.getCountryNameById(device.getDeviceCountryId())))
                .deviceLocation(device.getDeviceLocation())
                .deviceLongitude(device.getDeviceLongitude())
                .deviceLatitude(device.getDeviceLatitude())
                .deviceImg(device.getDeviceImg())
                .deviceVideo(device.getDeviceVideo())
                .deviceIntroduce(device.getDeviceIntroduce())
                .deviceInsqlTime(device.getDeviceInsqlTime())
                .deviceChangesqlTime(device.getDeviceChangesqlTime())
                .auditFlag(device.getAuditFlag())
                .build();
    }

    public List<NewsVO> LDevice2LNewsVO(List<Device> deviceList) {
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

    public List<DeviceVO> LDevice2LDeviceVO(List<Device> deviceList) {
        List<DeviceVO> deviceVOList = new ArrayList<>();
        for (Device device : deviceList) {
            deviceVOList.add(Device2DeviceVO(device));
        }
        return deviceVOList;
    }

    public List<FullDeviceVO> LDevice2LFDeviceVO(List<Device> deviceList) {
        List<FullDeviceVO> deviceVOList = new ArrayList<>();
        for (Device device : deviceList) {
            deviceVOList.add(Device2FDeviceVO(device));
        }
        return deviceVOList;
    }
}
