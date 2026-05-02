package com.wifi32767.domain.entry.service;

import com.wifi32767.domain.entry.model.DeviceJsonVO;
import com.wifi32767.domain.portal.model.DeviceVO;

import java.util.List;

public interface EntryService {
    void single(DeviceVO device);

    void batch(List<DeviceJsonVO> deviceList);
}
