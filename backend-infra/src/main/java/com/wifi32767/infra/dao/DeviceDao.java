package com.wifi32767.infra.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wifi32767.domain.search.model.SearchParams;
import com.wifi32767.domain.system.model.AuditSearchParamsVO;
import com.wifi32767.infra.dao.po.Device;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface DeviceDao {
    List<Device> queryAll();

    Page<Device> queryAll(Page<Device> page);

    void insert(Device device);

    void update(Device device);

    void deleteById(Integer id);

    Integer queryAllCount();

    Integer queryCountByAuditFlag(Integer auditFlag);

    Integer queryCountByDate(LocalDate localdate);

    Integer queryCorrectionCountByDate(LocalDate localdate);

    List<Device> queryLatestNews(Integer limit);

    List<Device> queryLatestDevices(Integer limit);

    List<Device> queryRandomDevices(Integer limit);

    Page<Device> searchDevices(Page<Device> page, @Param("params") SearchParams params);

    List<Device> queryDevicesByTitleAndStatus(AuditSearchParamsVO params);

    List<Device> queryDevicesByTitleAndStatusWithPages(AuditSearchParamsVO params, int offset, int size);

    void audit(AuditSearchParamsVO params);
}
