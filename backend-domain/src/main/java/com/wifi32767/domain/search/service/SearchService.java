package com.wifi32767.domain.search.service;

import com.wifi32767.common.PageData;
import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.domain.portal.model.NewsVO;
import com.wifi32767.domain.search.model.SearchParamsVO;

import java.util.Map;

public interface SearchService {

    PageData<DeviceVO> searchDevice(SearchParamsVO params);

    PageData<NewsVO> searchNews(SearchParamsVO params);

    Map<String, Integer> getClassStats(SearchParamsVO params);

    Map<String, Integer> getCountryStats(SearchParamsVO params);

    Map<Integer, Integer> getYearStats(SearchParamsVO params);

    Map<String, Integer> getCompanyRelationStats(SearchParamsVO params);
}
