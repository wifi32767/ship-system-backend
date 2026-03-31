package com.wifi32767.interfaces.http;

import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.domain.portal.model.SearchParamsVO;
import com.wifi32767.interfaces.dto.ClassStatsDTO;
import com.wifi32767.interfaces.dto.CountryStatsDTO;
import com.wifi32767.interfaces.dto.EchartsOptionDTO;
import com.wifi32767.interfaces.response.Response;

import java.util.List;

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
     * @return 类别统计 {@code List<com.wifi32767.interfaces.dto.ClassStatsDTO>}
     */
    Response<List<ClassStatsDTO>> getClassStats(SearchParamsVO req);

    /**
     * 类别分析：基于类别统计计算饼图所需的 EchartsOption。
     * 数据格式：JSON
     *
     * @param req 查询请求 DTO {@link SearchParamsVO}
     * @return Echarts 配置 DTO {@link EchartsOptionDTO}
     */
    Response<EchartsOptionDTO> getClassAnalysis(SearchParamsVO req);

    /**
     * 所属国家统计：对检索结果进行所属国家统计，返回 Map<country, number>。
     * 数据格式：JSON
     *
     * @param req 查询请求 DTO {@link SearchParamsVO}
     * @return 国家统计 {@code List<com.wifi32767.interfaces.dto.CountryStatsDTO>}
     */
    Response<List<CountryStatsDTO>> getCountryStats(SearchParamsVO req);

    /**
     * 所属国家分析：基于国家统计计算柱状图所需的 EchartsOption。
     * 数据格式：JSON
     *
     * @param req 查询请求 DTO {@link SearchParamsVO}
     * @return Echarts 配置 DTO {@link com.wifi32767.interfaces.dto.EchartsOptionDTO}
     */
    Response<EchartsOptionDTO> getCountryAnalysis(SearchParamsVO req);

    /**
     * 投产年份分析：返回用于前端折线图的 EchartsOption。
     * 数据格式：JSON
     *
     * @param req 查询请求 DTO {@link SearchParamsVO}
     * @return Echarts 配置 DTO {@link com.wifi32767.interfaces.dto.EchartsOptionDTO}
     */
    Response<EchartsOptionDTO> getYearAnalysis(SearchParamsVO req);

    /**
     * 企业关联关系分析：对检索到的相关企业进行可视化分析。
     * 数据格式：JSON
     *
     * @param req 查询请求 DTO {@link SearchParamsVO}
     * @return Echarts 配置 DTO {@link com.wifi32767.interfaces.dto.EchartsOptionDTO}
     */
    Response<EchartsOptionDTO> getCompanyRelationAnalysis(SearchParamsVO req);

}
