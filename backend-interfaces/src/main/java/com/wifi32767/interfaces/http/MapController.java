package com.wifi32767.interfaces.http;

import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.domain.search.model.SearchParamsVO;
import com.wifi32767.interfaces.response.Response;

import java.util.List;

public interface MapController {
    /**
     * 地图检索：支持全文检索、名称检索、类别检索、所属国家检索，返回地图标注点列表。
     * 数据格式：JSON
     *
     * @param req 查询请求 DTO {@link SearchParamsVO}
     * @return 地图标注设备列表 {@code List<com.wifi32767.domain.portal.model.DeviceVO>}
     */
    Response<List<DeviceVO>> mapSearch(SearchParamsVO req);

}
