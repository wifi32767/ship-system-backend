package com.wifi32767.interfaces.http.admin;

import com.wifi32767.common.Response;
import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.interfaces.dto.DeviceDTO;

import java.util.List;

public interface ModifyController {

    /**
     * 获取信息数量接口：返回当前数据库中信息的数量。
     * 数据格式：JSON
     *
     * @return 信息数量 {@code Integer}
     */
    Response<Integer> getDeviceCount();

    /**
     * 获取信息列表接口：返回所有信息数据。
     * 数据格式：JSON
     *
     * @return 信息列表 {@code List<DeviceDTO>}
     */
    Response<List<DeviceDTO>> getDeviceList();

    /**
     * 获取信息列表接口（分页）：返回指定页码和每页大小的数据。
     * 数据格式：JSON
     *
     * @param page 页码，从1开始
     * @param size 每页大小
     * @return 信息列表 {@code List<DeviceDTO>}
     */
    Response<List<DeviceDTO>> getDeviceList(int page, int size);

    /**
     * 修改信息接口：更新指定的信息。
     * 数据格式：JSON
     *
     * @param deviceVO 信息对象 {@link DeviceVO}
     * @return 修改操作结果消息 {@code String}
     */
    Response<String> modifyDevice(DeviceVO deviceVO);

    /**
     * 删除信息接口：删除指定的信息。
     * 数据格式：JSON
     *
     * @param id 信息ID
     * @return 删除操作结果消息 {@code String}
     */
    Response<String> deleteDevice(Integer id);
}
