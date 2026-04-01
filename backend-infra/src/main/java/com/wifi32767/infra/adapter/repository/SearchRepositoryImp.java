package com.wifi32767.infra.adapter.repository;

import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.domain.portal.model.NewsVO;
import com.wifi32767.domain.search.adapter.repository.SearchRepository;
import com.wifi32767.infra.adapter.converter.DeviceConverter;
import com.wifi32767.infra.dao.CountryDao;
import com.wifi32767.infra.dao.DeviceDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SearchRepositoryImp implements SearchRepository {
    @Resource
    private DeviceDao deviceDao;

    @Resource
    private CountryDao countryDao;

    @Override
    public List<DeviceVO> searchDeviceByText(String param) {
        return DeviceConverter.LDevice2LDeviceVO(deviceDao.queryDevicesByText(param));
    }

    @Override
    public List<DeviceVO> searchDeviceByTitle(String param) {
        return DeviceConverter.LDevice2LDeviceVO(deviceDao.queryDevicesByTitle(param));
    }

    @Override
    public List<DeviceVO> searchDeviceByType(int param) {
        return DeviceConverter.LDevice2LDeviceVO(deviceDao.queryDevicesByType(param));
    }

    @Override
    public List<DeviceVO> searchDeviceByStyle(int param) {
        return DeviceConverter.LDevice2LDeviceVO(deviceDao.queryDevicesByStyle(param));
    }

    @Override
    public List<DeviceVO> searchDeviceByClass(int param) {
        return DeviceConverter.LDevice2LDeviceVO(deviceDao.queryDevicesByClass(param));
    }

    @Override
    public List<DeviceVO> searchDeviceByCountry(String param) {
        int countryId = countryDao.queryCountryIdByName(param);
        if (countryId == -1) {
            return new ArrayList<>();
        }
        return DeviceConverter.LDevice2LDeviceVO(deviceDao.queryDevicesByCountry(countryId));
    }

    @Override
    public List<NewsVO> searchNewsByText(String param) {
        return DeviceConverter.LDevice2LNewsVO(deviceDao.queryDevicesByText(param));
    }

    @Override
    public List<NewsVO> searchNewsByTitle(String param) {
        return DeviceConverter.LDevice2LNewsVO(deviceDao.queryDevicesByTitle(param));
    }

    @Override
    public List<NewsVO> searchNewsByType(int param) {
        return DeviceConverter.LDevice2LNewsVO(deviceDao.queryDevicesByType(param));
    }

    @Override
    public List<NewsVO> searchNewsByStyle(int param) {
        return DeviceConverter.LDevice2LNewsVO(deviceDao.queryDevicesByStyle(param));
    }

    @Override
    public List<NewsVO> searchNewsByClass(int param) {
        return DeviceConverter.LDevice2LNewsVO(deviceDao.queryDevicesByClass(param));
    }

    @Override
    public List<NewsVO> searchNewsByCountry(String param) {
        int countryId = countryDao.queryCountryIdByName(param);
        if (countryId == -1) {
            return new ArrayList<>();
        }
        return DeviceConverter.LDevice2LNewsVO(deviceDao.queryDevicesByCountry(countryId));
    }
}
