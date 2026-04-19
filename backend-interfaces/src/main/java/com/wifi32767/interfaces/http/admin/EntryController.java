package com.wifi32767.interfaces.http.admin;

import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.interfaces.common.Response;
import com.wifi32767.interfaces.dto.EntryBatchRespDTO;

import java.util.List;

public interface EntryController {
    /**
     * 单条数据录入
     * 数据格式：JSON
     *
     * @param device
     * @return
     */
    Response<String> entry(DeviceVO device);

    /**
     * 批量数据录入，支持xlsx、csv格式
     * 数据格式：JSON
     *
     * @param devices
     * @return
     */
    Response<EntryBatchRespDTO> entryBatch(List<DeviceVO> devices);
}
