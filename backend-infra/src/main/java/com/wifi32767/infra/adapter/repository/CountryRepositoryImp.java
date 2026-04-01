package com.wifi32767.infra.adapter.repository;

import com.wifi32767.domain.portal.adapter.repository.CountryRepository;
import com.wifi32767.infra.dao.CountryDao;
import com.wifi32767.infra.redis.RedisService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class CountryRepositoryImp implements CountryRepository {
    private static final String COUNTRY_ID_KEY_PREFIX = "country_id:";
    @Resource
    private CountryDao countryDao;
    @Resource
    private RedisService redisService;

    @Override
    public String getCountryNameById(int countryId) {
        if (redisService.isExists(COUNTRY_ID_KEY_PREFIX + countryId)) {
            return redisService.getValue(COUNTRY_ID_KEY_PREFIX + countryId);
        }
        String countryName = countryDao.queryCountryNameById(countryId);
        if (countryName != null) {
            redisService.setValue(COUNTRY_ID_KEY_PREFIX + countryId, countryName);
        }
        return countryName;
    }
}
