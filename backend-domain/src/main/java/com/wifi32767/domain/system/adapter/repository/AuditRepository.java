package com.wifi32767.domain.system.adapter.repository;

import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.domain.system.model.AuditSearchParamsVO;

import java.util.List;

public interface AuditRepository {
    void audit(AuditSearchParamsVO params);

    List<DeviceVO> searchAuditDevices(AuditSearchParamsVO params);
}
