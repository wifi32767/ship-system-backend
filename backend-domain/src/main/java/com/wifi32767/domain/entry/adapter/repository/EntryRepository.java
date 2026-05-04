package com.wifi32767.domain.entry.adapter.repository;

import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.domain.system.model.EntryLogVO;

import java.util.List;

public interface EntryRepository {
    void single(DeviceVO device);

    EntryLogVO batch(List<DeviceVO> devices);
}
