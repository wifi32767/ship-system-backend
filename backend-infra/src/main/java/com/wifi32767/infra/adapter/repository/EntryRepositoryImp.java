package com.wifi32767.infra.adapter.repository;

import com.wifi32767.domain.context.UserContext;
import com.wifi32767.domain.entry.adapter.repository.EntryRepository;
import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.domain.system.model.EntryLogVO;
import com.wifi32767.infra.adapter.converter.DeviceConverter;
import com.wifi32767.infra.dao.DeviceDao;
import com.wifi32767.infra.redis.RedisService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class EntryRepositoryImp implements EntryRepository {
    @Resource
    private RedisService redisService;

    @Resource
    private DeviceDao deviceDao;

    @Resource
    private DeviceConverter deviceConverter;

    @Override
    public void single(DeviceVO deviceVO) {
        deviceVO.setDeviceInsqlTime(LocalDateTime.now());
        deviceVO.setDeviceChangesqlTime(LocalDateTime.now());
        deviceDao.insert(deviceConverter.DeviceVO2Device(deviceVO));
        DeviceCache.removeAllList(redisService);
    }

    @Override
    public EntryLogVO batch(List<DeviceVO> devices) {
        StringBuilder log = new StringBuilder();
        int count = 0;
        for (DeviceVO deviceVO : devices) {
            deviceVO.setDeviceInsqlTime(LocalDateTime.now());
            deviceVO.setDeviceChangesqlTime(LocalDateTime.now());
            try {
                deviceDao.insert(deviceConverter.DeviceVO2Device(deviceVO));
            } catch (Exception e) {
                log.append(String.format("device: %s, error: %s\n", deviceVO.toString(), e.getMessage()));
            } finally {
                count++;
            }
        }
        DeviceCache.removeAllList(redisService);
        return EntryLogVO.builder()
                .csvEnterLogs(log.toString())
                .csvEnterNumber(devices.size())
                .csvEnterSuccessNumber(count)
                .csvEnterLogsTime(LocalDateTime.now())
                .csvEnterUserName(String.valueOf(UserContext.get().getUserId()))
                .build();
    }
}
