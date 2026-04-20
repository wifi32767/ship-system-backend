package com.wifi32767.domain.system.service;

import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.domain.system.model.AuditSearchParamsVO;

import java.util.List;

public interface AuditService {

    List<DeviceVO> searchAuditDevices(AuditSearchParamsVO params);

    void audit(AuditSearchParamsVO params);
}
