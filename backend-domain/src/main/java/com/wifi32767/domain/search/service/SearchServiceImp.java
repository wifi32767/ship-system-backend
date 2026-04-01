package com.wifi32767.domain.search.service;

import com.wifi32767.domain.portal.adapter.repository.ClassRepository;
import com.wifi32767.domain.portal.adapter.repository.CountryRepository;
import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.domain.portal.model.NewsVO;
import com.wifi32767.domain.portal.service.PortalService;
import com.wifi32767.domain.search.adapter.repository.SearchRepository;
import com.wifi32767.domain.search.model.SearchParamsVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImp implements SearchService {

    @Resource
    private SearchRepository searchRepository;

    @Resource
    private ClassRepository classRepository;

    @Resource
    private CountryRepository countryRepository;

    @Resource
    private PortalService portalService;

    @Override
    public List<DeviceVO> searchDevice(SearchParamsVO params) {
        if (params.getKeyword() != null) {
            return searchDeviceByText(params.getKeyword());
        } else if (params.getTitle() != null) {
            return searchDeviceByTitle(params.getTitle());
        } else if (params.getTypeId() != null) {
            return searchDeviceByType(params.getTypeId());
        } else if (params.getStyleId() != null) {
            return searchDeviceByStyle(params.getStyleId());
        } else if (params.getClassId() != null) {
            return searchDeviceByClass(params.getClassId());
        } else if (params.getCountry() != null) {
            return searchDeviceByCountry(params.getCountry());
        }
        return portalService.getLatestDevices(-1);
    }

    private List<DeviceVO> searchDeviceByText(String param) {
        return searchRepository.searchDeviceByText(param);
    }

    private List<DeviceVO> searchDeviceByTitle(String param) {
        return searchRepository.searchDeviceByTitle(param);
    }

    private List<DeviceVO> searchDeviceByType(int param) {
        return searchRepository.searchDeviceByType(param);
    }

    private List<DeviceVO> searchDeviceByStyle(int param) {
        return searchRepository.searchDeviceByStyle(param);
    }

    private List<DeviceVO> searchDeviceByClass(int param) {
        return searchRepository.searchDeviceByClass(param);
    }

    private List<DeviceVO> searchDeviceByCountry(String param) {
        return searchRepository.searchDeviceByCountry(param);
    }

    @Override
    public List<NewsVO> searchNews(SearchParamsVO params) {
        if (params.getKeyword() != null) {
            return searchNewsByText(params.getKeyword());
        } else if (params.getTitle() != null) {
            return searchNewsByTitle(params.getTitle());
        } else if (params.getTypeId() != null) {
            return searchNewsByType(params.getTypeId());
        } else if (params.getStyleId() != null) {
            return searchNewsByStyle(params.getStyleId());
        } else if (params.getClassId() != null) {
            return searchNewsByClass(params.getClassId());
        } else if (params.getCountry() != null) {
            return searchNewsByCountry(params.getCountry());
        }
        return portalService.getLatestNews(-1);
    }

    private List<NewsVO> searchNewsByText(String param) {
        return searchRepository.searchNewsByText(param);
    }

    private List<NewsVO> searchNewsByTitle(String param) {
        return searchRepository.searchNewsByTitle(param);
    }

    private List<NewsVO> searchNewsByType(int param) {
        return searchRepository.searchNewsByType(param);
    }

    private List<NewsVO> searchNewsByStyle(int param) {
        return searchRepository.searchNewsByStyle(param);
    }

    private List<NewsVO> searchNewsByClass(int param) {
        return searchRepository.searchNewsByClass(param);
    }

    private List<NewsVO> searchNewsByCountry(String param) {
        return searchRepository.searchNewsByCountry(param);
    }

    @Override
    public Map<String, Integer> getClassStats(SearchParamsVO params) {
        List<DeviceVO> devices = searchDevice(params);
        Map<String, Integer> stats = new HashMap<>();
        for (DeviceVO device : devices) {
            int classId = device.getDeviceClassId();
            // 可以优化
            String className = classRepository.getClassNameById(classId);
            stats.put(className, stats.getOrDefault(className, 0) + 1);
        }
        return stats;
    }

    @Override
    public Map<String, Integer> getCountryStats(SearchParamsVO params) {
        List<DeviceVO> devices = searchDevice(params);
        Map<String, Integer> stats = new HashMap<>();
        for (DeviceVO device : devices) {
            int countryId = device.getDeviceCountryId();
            // 可以优化
            String country = countryRepository.getCountryNameById(countryId);
            stats.put(country, stats.getOrDefault(country, 0) + 1);
        }
        return stats;
    }

    @Override
    public Map<Integer, Integer> getYearStats(SearchParamsVO params) {
        List<DeviceVO> devices = searchDevice(params);
        Map<Integer, Integer> stats = new HashMap<>();
        for (DeviceVO device : devices) {
            Integer year = device.getDeviceUseYear();
            stats.put(year, stats.getOrDefault(year, 0) + 1);
        }
        return stats;
    }

    @Override
    public Map<String, Integer> getCompanyRelationStats(SearchParamsVO params) {
        List<DeviceVO> devices = searchDevice(params);
        Map<String, Integer> stats = new HashMap<>();
        for (DeviceVO device : devices) {
            String companyRelation = device.getDeviceUsingUnit();
            stats.put(companyRelation, stats.getOrDefault(companyRelation, 0) + 1);
        }
        return stats;
    }
}
