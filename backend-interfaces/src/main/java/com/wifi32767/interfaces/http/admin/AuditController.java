package com.wifi32767.interfaces.http.admin;

import com.wifi32767.common.Response;
import com.wifi32767.domain.system.model.AuditSearchParamsVO;
import com.wifi32767.domain.system.model.FullDeviceVO;

import java.util.List;

public interface AuditController {
    /**
     * 搜索录入信息：根据标题和状态获取录入信息
     * 数据格式：JSON
     *
     * @param params 标题和状态参数 {@link AuditSearchParamsVO}
     * @return 录入信息列表 {@link List<FullDeviceVO>}
     */
    Response<List<FullDeviceVO>> searchAuditDevices(AuditSearchParamsVO params);

    /**
     * 搜索录入信息：根据标题和状态分页获取录入信息
     * 数据格式：JSON
     *
     * @param params 标题和状态参数 {@link AuditSearchParamsVO}
     * @param page   页码
     * @param size   每页大小
     * @return 录入信息列表 {@link List<FullDeviceVO>}
     */
    Response<List<FullDeviceVO>> searchAuditDevices(AuditSearchParamsVO params, int page, int size);

    /**
     * 录入信息审核：审核录入信息
     * 数据格式：JSON
     *
     * @param params 录入信息参数(使用id和状态信息) {@link AuditSearchParamsVO}
     * @return 操作结果消息 {@code String}
     */
    Response<String> audit(AuditSearchParamsVO params);
}
