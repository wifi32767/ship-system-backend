package com.wifi32767.domain.system.service;

import com.wifi32767.domain.system.model.AuditSearchParamsVO;
import com.wifi32767.domain.system.model.FullDeviceVO;

import java.util.List;

public interface AuditService {

    List<FullDeviceVO> searchAuditDevices(AuditSearchParamsVO params);

    List<FullDeviceVO> searchAuditDevices(AuditSearchParamsVO params, int page, int size);

    void audit(AuditSearchParamsVO params);
}
