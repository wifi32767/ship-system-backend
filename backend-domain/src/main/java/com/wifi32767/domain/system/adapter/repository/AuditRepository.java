package com.wifi32767.domain.system.adapter.repository;

import com.wifi32767.domain.system.model.AuditSearchParamsVO;
import com.wifi32767.domain.system.model.FullDeviceVO;

import java.util.List;

public interface AuditRepository {
    void audit(AuditSearchParamsVO params);

    List<FullDeviceVO> searchAuditDevices(AuditSearchParamsVO params);

    List<FullDeviceVO> searchAuditDevicesPages(AuditSearchParamsVO params, int page, int size);
}
