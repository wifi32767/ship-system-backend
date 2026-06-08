package com.wifi32767.domain.search.service;

import com.wifi32767.common.PageData;
import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.domain.portal.model.NewsVO;
import com.wifi32767.domain.search.adapter.repository.SearchRepository;
import com.wifi32767.domain.search.model.SearchParamsVO;
import com.wifi32767.domain.system.adapter.repository.ClassRepository;
import com.wifi32767.domain.system.adapter.repository.CountryRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class SearchServiceImp implements SearchService {

    @Resource
    private SearchRepository searchRepository;

    @Resource
    private ClassRepository classRepository;

    @Resource
    private CountryRepository countryRepository;

    @Override
    public PageData<DeviceVO> searchDevice(SearchParamsVO params) {
        if (params == null) {
            return null;
        }
        return searchRepository.searchDevices(params);
    }

    @Override
    public PageData<NewsVO> searchNews(SearchParamsVO params) {
        if (params == null) {
            return null;
        }
        return searchRepository.searchNews(params);
    }

    @Override
    public Map<String, Integer> getClassStats(SearchParamsVO params) {
        if (params == null) {
            return null;
        }
        params.setPageNum(1);
        params.setPageSize(Integer.MAX_VALUE);
        PageData<DeviceVO> devices = searchDevice(params);
        Map<String, Integer> stats = new HashMap<>();
        for (DeviceVO device : devices.getRecords()) {
            Integer classId = device.getDeviceClassId();
            if (classId == null) {
                continue;
            }
            String className = classRepository.getClassNameById(classId);
            if (StringUtils.isEmpty(className)) {
                continue;
            }
            stats.put(className, stats.getOrDefault(className, 0) + 1);
        }
        return stats;
    }

    @Override
    public Map<String, Integer> getCountryStats(SearchParamsVO params) {
        if (params == null) {
            return null;
        }
        params.setPageNum(1);
        params.setPageSize(Integer.MAX_VALUE);
        PageData<DeviceVO> devices = searchDevice(params);
        Map<String, Integer> stats = new HashMap<>();
        for (DeviceVO device : devices.getRecords()) {
            Integer countryId = device.getDeviceCountryId();
            if (countryId == null) {
                continue;
            }
            String countryName = countryRepository.getCountryNameById(countryId);
            if (StringUtils.isEmpty(countryName)) {
                continue;
            }
            stats.put(countryName, stats.getOrDefault(countryName, 0) + 1);
        }
        return stats;
    }

    @Override
    public Map<Integer, Integer> getYearStats(SearchParamsVO params) {
        if (params == null) {
            return null;
        }
        params.setPageNum(1);
        params.setPageSize(Integer.MAX_VALUE);
        PageData<DeviceVO> devices = searchDevice(params);
        Map<Integer, Integer> stats = new HashMap<>();
        for (DeviceVO device : devices.getRecords()) {
            Integer year = device.getDeviceUseYear();
            if (year == null) {
                continue;
            }
            stats.put(year, stats.getOrDefault(year, 0) + 1);
        }
        return stats;
    }

    @Override
    public Map<String, Integer> getCompanyRelationStats(SearchParamsVO params) {
        if (params == null) {
            return null;
        }
        params.setPageNum(1);
        params.setPageSize(Integer.MAX_VALUE);
        PageData<DeviceVO> devices = searchDevice(params);
        Map<String, Integer> stats = new HashMap<>();
        for (DeviceVO device : devices.getRecords()) {
            String companyRelation = device.getDeviceUsingUnit();
            stats.put(companyRelation, stats.getOrDefault(companyRelation, 0) + 1);
        }
        return stats;
    }
}
