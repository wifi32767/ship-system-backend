package com.wifi32767.domain.entry.adapter.repository;

import com.wifi32767.domain.portal.model.DeviceVO;

import java.util.List;

public interface EntryRepository {
    void single(DeviceVO device);

    void batch(List<DeviceVO> devices);
}
