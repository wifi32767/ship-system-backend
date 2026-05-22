package com.wifi32767.infra.adapter.repository;

import com.wifi32767.domain.system.adapter.repository.AuditRepository;
import com.wifi32767.domain.system.model.AuditSearchParamsVO;
import com.wifi32767.domain.system.model.FullDeviceVO;
import com.wifi32767.infra.adapter.converter.DeviceConverter;
import com.wifi32767.infra.dao.DeviceDao;
import com.wifi32767.infra.redis.RedisService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class AuditRepositoryImp implements AuditRepository {

    @Resource
    private RedisService redisService;

    @Resource
    private DeviceDao deviceDao;

    @Resource
    private DeviceConverter deviceConverter;

    @Override
    public List<FullDeviceVO> searchAuditDevices(AuditSearchParamsVO params) {
        return deviceConverter.LDevice2LFDeviceVO(deviceDao.queryDevicesByTitleAndStatus(params));
    }

    @Override
    public List<FullDeviceVO> searchAuditDevicesPages(AuditSearchParamsVO params, int page, int size) {
        return deviceConverter.LDevice2LFDeviceVO(deviceDao.queryDevicesByTitleAndStatusWithPages(params, (page - 1) * size, size));
    }

    @Override
    public void audit(AuditSearchParamsVO params) {
        deviceDao.audit(params);
        DeviceCache.removeAllList(redisService);
    }
}
