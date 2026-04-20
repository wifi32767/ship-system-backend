package com.wifi32767.infra.adapter.repository;

import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.domain.system.adapter.repository.AuditRepository;
import com.wifi32767.domain.system.model.AuditSearchParamsVO;
import com.wifi32767.infra.adapter.converter.DeviceConverter;
import com.wifi32767.infra.dao.DeviceDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class AuditRepositoryImp implements AuditRepository {

    @Resource
    private DeviceDao deviceDao;

    @Override
    public List<DeviceVO> queryAuditDevices(AuditSearchParamsVO params) {
        return DeviceConverter.LDevice2LDeviceVO(deviceDao.queryDevicesByTitleAndStatus(params));
    }

    @Override
    public void audit(AuditSearchParamsVO params) {
        deviceDao.audit(params);
    }
}
