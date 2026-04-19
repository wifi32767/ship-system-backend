package com.wifi32767.interfaces.http.admin;

import com.wifi32767.domain.system.model.CountryVO;
import com.wifi32767.infra.dao.po.Country;
import com.wifi32767.interfaces.common.Response;

import java.util.List;

public interface CountryController {
    /**
     * 获取国家列表接口：返回所有国家信息。
     * 数据格式：JSON
     *
     * @return 国家信息列表 {@code List<CountryVO>}
     */
    Response<List<CountryVO>> countryGet();

    /**
     * 添加国家接口：创建新的国家记录。
     * 数据格式：JSON
     *
     * @param country 国家信息对象 {@link Country}
     * @return 操作结果消息 {@code String}
     */
    Response<String> countryInsert(Country country);

    /**
     * 删除国家接口：根据ID删除指定国家记录。
     * 数据格式：JSON
     *
     * @param countryId 目标国家ID
     * @return 操作结果消息 {@code String}
     */
    Response<String> countryDelete(Integer countryId);

    /**
     * 批量删除国家接口：根据ID列表批量删除国家记录。
     * 数据格式：JSON
     *
     * @param countryIds 目标国家ID列表
     * @return 操作结果消息 {@code String}
     */
    Response<String> countryBatchDelete(List<Integer> countryIds);

    /**
     * 修改国家接口：更新指定国家的信息。
     * 数据格式：JSON
     *
     * @param country 包含更新信息的国家对象 {@link Country}
     * @return 操作结果消息 {@code String}
     */
    Response<String> countryEdit(Country country);
}
