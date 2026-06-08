package com.wifi32767.infra.adapter.repository;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wifi32767.common.PageData;
import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.domain.portal.model.NewsVO;
import com.wifi32767.domain.search.adapter.repository.SearchRepository;
import com.wifi32767.domain.search.model.SearchParams;
import com.wifi32767.domain.search.model.SearchParamsVO;
import com.wifi32767.infra.adapter.converter.DeviceConverter;
import com.wifi32767.infra.dao.CountryDao;
import com.wifi32767.infra.dao.DeviceDao;
import com.wifi32767.infra.dao.po.Device;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class SearchRepositoryImp implements SearchRepository {
    @Resource
    private DeviceDao deviceDao;

    @Resource
    private CountryDao countryDao;

    @Resource
    private DeviceConverter deviceConverter;

    @Override
    public PageData<DeviceVO> searchDevices(SearchParamsVO params) {
        SearchParams param1 = SearchParams.builder()
                .keyword(params.getKeyword())
                .introduce(params.getIntroduce())
                .typeId(params.getTypeId())
                .styleId(params.getStyleId())
                .classId(params.getClassId())
                .countryId(countryDao.queryCountryIdByName(params.getCountry()))
                .startDate(params.getStartDate())
                .endDate(params.getEndDate())
                .build();
        Page<Device> page = new Page<>(params.getPageNum(), params.getPageSize());
        return new PageData<>(
                deviceConverter.LDevice2LDeviceVO(deviceDao.searchDevices(page, param1).getRecords()),
                page.getTotal(),
                page.getSize(),
                page.getCurrent()
        );
    }

    @Override
    public PageData<NewsVO> searchNews(SearchParamsVO params) {
        SearchParams param1 = SearchParams.builder()
                .keyword(params.getKeyword())
                .introduce(params.getIntroduce())
                .typeId(params.getTypeId())
                .styleId(params.getStyleId())
                .classId(params.getClassId())
                .countryId(countryDao.queryCountryIdByName(params.getCountry()))
                .startDate(params.getStartDate())
                .endDate(params.getEndDate())
                .build();
        Page<Device> page = new Page<>(params.getPageNum(), params.getPageSize());
        return new PageData<>(
                deviceConverter.LDevice2LNewsVO(deviceDao.searchDevices(page, param1).getRecords()),
                page.getTotal(),
                page.getSize(),
                page.getCurrent()
        );
    }
}
