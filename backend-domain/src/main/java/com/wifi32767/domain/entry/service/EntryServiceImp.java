package com.wifi32767.domain.entry.service;

import com.wifi32767.domain.context.UserContext;
import com.wifi32767.domain.entry.adapter.repository.EntryRepository;
import com.wifi32767.domain.entry.model.DeviceJsonVO;
import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.domain.system.adapter.repository.ClassRepository;
import com.wifi32767.domain.system.adapter.repository.CountryRepository;
import com.wifi32767.domain.system.adapter.repository.LogRepository;
import com.wifi32767.domain.system.model.EntryLogVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EntryServiceImp implements EntryService {

    @Resource
    private EntryRepository entryRepository;

    @Resource
    private CountryRepository countryRepository;

    @Resource
    private ClassRepository classRepository;

    @Resource
    private LogRepository logRepository;

    @Override
    public void single(DeviceVO device) {
        try {
            entryRepository.single(device);
        } catch (Exception e) {
            logRepository.insertLog(EntryLogVO.builder()
                    .csvEnterLogs(String.format("device: %s, error: %s", device.toString(), e.getMessage()))
                    .csvEnterNumber(1)
                    .csvEnterSuccessNumber(0)
                    .csvEnterLogsTime(LocalDateTime.now())
                    .csvEnterUserName(String.valueOf(UserContext.get().getUserId()))
                    .build());
            throw e;
        } finally {
            logRepository.insertLog(EntryLogVO.builder()
                    .csvEnterLogs("success")
                    .csvEnterNumber(1)
                    .csvEnterSuccessNumber(1)
                    .csvEnterLogsTime(LocalDateTime.now())
                    .csvEnterUserName(String.valueOf(UserContext.get().getUserId()))
                    .build());
        }
    }

    // TODO: 这个接口可以设计返回插入情况报告，哪些数据匹配失败
    @Override
    public void batch(List<DeviceJsonVO> deviceList) {
        List<DeviceVO> deviceVOList = deviceList.stream().map(
                deviceJsonVO -> {
                    DeviceVO deviceVO = DeviceVO.builder()
                            .id(deviceJsonVO.getId())
                            .deviceName(deviceJsonVO.getDeviceName())
                            .deviceUseYear(deviceJsonVO.getDeviceUseYear())
                            .devicePrice(deviceJsonVO.getDevicePrice())
                            .deviceUsingUnit(deviceJsonVO.getDeviceUsingUnit())
                            .deviceCountryId(countryRepository.getCountryIdByName(deviceJsonVO.getDeviceCountryName()))
                            .deviceLocation(deviceJsonVO.getDeviceLocation())
                            .deviceLongitude(deviceJsonVO.getDeviceLongitude())
                            .deviceLatitude(deviceJsonVO.getDeviceLatitude())
                            .deviceImg(deviceJsonVO.getDeviceImg())
                            .deviceVideo(deviceJsonVO.getDeviceVideo())
                            .deviceIntroduce(deviceJsonVO.getDeviceIntroduce())
                            .deviceInsqlTime(LocalDateTime.now())
                            .deviceChangesqlTime(LocalDateTime.now())
                            .auditFlag(0)
                            .build();
                    Integer classId = classRepository.getClassIdByName(deviceJsonVO.getDeviceClassName());
                    if (classId != null) {
                        deviceVO.setDeviceClassId(classId);
                        Integer styleId = classRepository.getStyleIdByName(deviceJsonVO.getDeviceStyleName(), classId);
                        if (styleId != null) {
                            deviceVO.setDeviceStyleId(styleId);
                            deviceVO.setDeviceTypeId(classRepository.getTypeIdByName(deviceJsonVO.getDeviceTypeName(), styleId));
                        }
                    }
                    return deviceVO;
                }
        ).toList();
        logRepository.insertLog(entryRepository.batch(deviceVOList));
    }
}
