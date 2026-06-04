package com.wifi32767.domain.system.service;

import com.wifi32767.common.PageData;
import com.wifi32767.domain.portal.model.DeviceVO;

import java.util.List;

public interface DeviceService {

    int deviceCount();

    int auditDeviceCount(int type);

    List<DeviceVO> getDeviceList();

    PageData<DeviceVO> getDeviceList(int pageNum, int pageSize);

    void modifyDevice(DeviceVO deviceVO);

    void deleteDevice(Integer deviceId);
}
