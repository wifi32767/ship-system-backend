package com.wifi32767.domain.portal.service;

import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.domain.portal.model.NewsVO;
import com.wifi32767.domain.portal.model.SearchParamsVO;

import java.util.List;
import java.util.Map;

public interface SearchService {
    List<DeviceVO> searchDevice(SearchParamsVO params);

    List<NewsVO> searchNews(SearchParamsVO params);

    Map<String, Integer> getClassStats(SearchParamsVO params);

    Map<String, Integer> getCountryStats(SearchParamsVO params);

    Map<Integer, Integer> getYearStats(SearchParamsVO params);

    Map<String, Integer> getCompanyRelationStats(SearchParamsVO params);
}
