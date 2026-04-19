package com.wifi32767.interfaces.http.admin;

import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.domain.system.model.AuditSearchParamsVO;
import com.wifi32767.interfaces.common.Response;

import java.util.List;

public interface AuditController {
    /**
     * 搜索录入信息：根据标题和状态获取录入信息
     * 数据格式：JSON
     *
     * @param params 标题和状态参数 {@link AuditSearchParamsVO}
     * @return 录入信息列表 {@link List<DeviceVO>}
     */
    Response<List<DeviceVO>> searchAuditDevices(AuditSearchParamsVO params);

    /**
     * 录入信息审核：审核录入信息
     * 数据格式：JSON
     *
     * @param params 录入信息参数(使用id和状态信息) {@link AuditSearchParamsVO}
     * @return 操作结果消息 {@code String}
     */
    Response<String> audit(AuditSearchParamsVO params);
}
