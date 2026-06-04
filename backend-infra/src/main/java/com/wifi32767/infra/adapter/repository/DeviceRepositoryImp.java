package com.wifi32767.infra.adapter.repository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wifi32767.common.PageData;
import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.domain.system.adapter.repository.DeviceRepository;
import com.wifi32767.infra.adapter.converter.DeviceConverter;
import com.wifi32767.infra.dao.DeviceDao;
import com.wifi32767.infra.dao.po.Device;
import com.wifi32767.infra.redis.RedisService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DeviceRepositoryImp implements DeviceRepository {

    @Resource
    private RedisService redisService;

    @Resource
    private DeviceDao deviceDao;

    @Resource
    private DeviceConverter deviceConverter;

    @Override
    public List<DeviceVO> getDeviceList() {
        List<DeviceVO> deviceVOList = redisService.getValue(DeviceCache.ALL_LIST);
        if (deviceVOList != null) {
            return deviceVOList;
        }
        deviceVOList = deviceConverter.LDevice2LDeviceVO(deviceDao.queryAll());
        redisService.setValue(DeviceCache.ALL_LIST, deviceVOList, DeviceCache.getExpireTime());
        return deviceVOList;
    }

    @Override
    public PageData<DeviceVO> getDeviceList(int pageNum, int pageSize) {
        String key = String.format(DeviceCache.ALL_LIST_PAGE, pageNum, pageSize);
        PageData<DeviceVO> deviceVOList = redisService.getValue(key);
        if (deviceVOList != null) {
            return deviceVOList;
        }
        Page<Device> page = deviceDao.queryAll(Page.of(pageNum, pageSize));
        deviceVOList = PageData.<DeviceVO>builder()
                .records(deviceConverter.LDevice2LDeviceVO(page.getRecords()))
                .total(page.getTotal())
                .current(pageNum)
                .size(pageSize)
                .build();
        redisService.setValue(key, deviceVOList, DeviceCache.getExpireTime());
        return deviceVOList;
    }

    @Override
    public void modifyDevice(DeviceVO deviceVO) {
        Device device = deviceConverter.DeviceVO2Device(deviceVO);
        device.setDeviceChangesqlTime(LocalDateTime.now());
        String key = DeviceCache.DEVICE_BY_ID + deviceVO.getId();
        deviceDao.update(device);
        redisService.remove(key);
        DeviceCache.removeAllList(redisService);
    }

    @Override
    public void deleteDevice(Integer deviceId) {
        String key = DeviceCache.DEVICE_BY_ID + deviceId;
        deviceDao.deleteById(deviceId);
        redisService.remove(key);
        DeviceCache.removeAllList(redisService);
    }
}
