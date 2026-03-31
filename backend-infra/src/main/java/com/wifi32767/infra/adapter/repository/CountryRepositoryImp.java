package com.wifi32767.infra.adapter.repository;

import com.wifi32767.domain.portal.adapter.repository.CountryRepository;
import com.wifi32767.infra.dao.CountryDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class CountryRepositoryImp implements CountryRepository {
    @Resource
    private CountryDao countryDao;

    @Override
    public String getCountryNameById(int countryId) {
        return countryDao.queryCountryNameById(countryId);
    }

}
