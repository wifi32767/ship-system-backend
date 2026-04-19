package com.wifi32767.interfaces.http.admin;

import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.interfaces.common.Response;

import java.util.List;

public interface ModifyController {

    /**
     * 获取信息列表接口：返回所有信息数据。
     * 数据格式：JSON
     *
     * @return 信息列表 {@code List<DeviceVO>}
     */
    Response<List<DeviceVO>> getDeviceList();

    /**
     * 获取信息列表接口（分页）：返回指定页码和每页大小的数据。
     * 数据格式：JSON
     *
     * @param page 页码，从1开始
     * @param size 每页大小
     * @return 信息列表 {@code List<DeviceVO>}
     */
    Response<List<DeviceVO>> getDeviceList(int page, int size);

    /**
     * 修改信息接口：更新指定的信息。
     * 数据格式：JSON
     *
     * @param deviceVO 信息对象 {@link DeviceVO}
     * @return 修改操作结果消息 {@code String}
     */
    Response<String> modifyDevice(DeviceVO deviceVO);
}
