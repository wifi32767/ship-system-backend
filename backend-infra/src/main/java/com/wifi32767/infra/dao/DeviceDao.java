package com.wifi32767.infra.dao;

import com.wifi32767.domain.system.model.AuditSearchParamsVO;
import com.wifi32767.infra.dao.po.Device;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface DeviceDao {
    Integer queryAllCount();

    Integer queryCountByDate(LocalDate localdate);

    Integer queryCorrectionCountByDate(LocalDate localdate);

    List<Device> queryLatestNews(Integer limit);

    List<Device> queryLatestDevices(Integer limit);

    List<Device> queryRandomDevices(Integer limit);

    List<Device> queryDevicesByText(String keyword);

    List<Device> queryDevicesByTitle(String keyword);

    List<Device> queryDevicesByType(Integer id);

    List<Device> queryDevicesByStyle(Integer id);

    List<Device> queryDevicesByClass(Integer id);

    List<Device> queryDevicesByCountry(Integer id);

    List<Device> queryDevicesByTitleAndStatus(AuditSearchParamsVO params);

    void audit(AuditSearchParamsVO params);
}
