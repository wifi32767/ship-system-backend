package com.wifi32767.domain.system.service;

import com.wifi32767.domain.system.adapter.repository.AuditRepository;
import com.wifi32767.domain.system.model.AuditSearchParamsVO;
import com.wifi32767.domain.system.model.FullDeviceVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AuditServiceImp implements AuditService {

    @Resource
    private AuditRepository auditRepository;

    @Override
    public List<FullDeviceVO> searchAuditDevices(AuditSearchParamsVO params) {
        if (params == null) {
            return null;
        }
        return auditRepository.searchAuditDevices(params);
    }

    @Override
    public List<FullDeviceVO> searchAuditDevices(AuditSearchParamsVO params, int page, int size) {
        if (params == null || page <= 0 || size <= 0) {
            return null;
        }
        return auditRepository.searchAuditDevicesPages(params, page, size);
    }

    @Override
    public void audit(AuditSearchParamsVO params) {
        if (params == null) {
            return;
        }
        auditRepository.audit(params);
    }
}
