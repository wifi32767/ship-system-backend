package com.wifi32767.domain.system.service;

import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.domain.system.adapter.repository.AuditRepository;
import com.wifi32767.domain.system.model.AuditSearchParamsVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AuditServiceImp implements AuditService {

    @Resource
    private AuditRepository auditRepository;

    @Override
    public List<DeviceVO> searchAuditDevices(AuditSearchParamsVO params) {
        return auditRepository.searchAuditDevices(params);
    }

    @Override
    public void audit(AuditSearchParamsVO params) {
        auditRepository.audit(params);
    }
}
