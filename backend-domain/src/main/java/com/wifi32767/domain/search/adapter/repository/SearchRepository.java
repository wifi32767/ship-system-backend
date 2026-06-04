package com.wifi32767.domain.search.adapter.repository;

import com.wifi32767.common.PageData;
import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.domain.portal.model.NewsVO;
import com.wifi32767.domain.search.model.SearchParamsVO;

public interface SearchRepository {
    PageData<DeviceVO> searchDevices(SearchParamsVO params);

    PageData<NewsVO> searchNews(SearchParamsVO params);
}
