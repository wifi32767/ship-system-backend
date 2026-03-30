package com.wifi32767.interfaces.http;

import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.domain.portal.model.SearchParamsVO;
import com.wifi32767.interfaces.response.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface SearchController {
    /**
     * 查询接口：支持全文检索、标题联想检索、类别检索、所属国家检索。
     * 检索内容为空时为全库查询，返回所有新闻信息。
     * 数据格式：JSON
     *
     * @param req 查询请求 DTO {@link SearchParamsVO}
     * @return 查询结果列表 {@code List<com.wifi32767.domain.portal.model.DeviceVO>}
     */
    Response<List<DeviceVO>> search(SearchParamsVO req);

    /**
     * 类别统计：对检索结果进行类别统计，返回 Map<className, number>。
     * 数据格式：JSON
     *
     * @param req 查询请求 DTO {@link SearchParamsVO}
     * @return 类别统计 Map
     */
    Response<Map<String,Integer>> getClassStats(SearchParamsVO req);
