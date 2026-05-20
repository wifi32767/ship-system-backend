package com.wifi32767.infra.adapter.repository;

import com.wifi32767.domain.system.adapter.repository.CountryRepository;
import com.wifi32767.domain.system.model.CountryVO;
import com.wifi32767.infra.dao.CountryDao;
import com.wifi32767.infra.dao.po.Country;
import com.wifi32767.infra.redis.RedisService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CountryRepositoryImp implements CountryRepository {
    private static final String COUNTRY_ID_KEY_PREFIX = "country_id:";
    private static final String COUNTRY_NAME_KEY_PREFIX = "country_name:";
    private static final String ALL_LIST_KEY = "country_all_list";
    @Resource
    private CountryDao countryDao;
    @Resource
    private RedisService redisService;

    @Override
    public String getCountryNameById(int countryId) {
        String countryName = redisService.getValue(COUNTRY_NAME_KEY_PREFIX + countryId);
        if (!countryName.isBlank()) {
            return countryName;
        }

        countryName = countryDao.queryCountryNameById(countryId);
        if (!countryName.isBlank()) {
            redisService.setValue(COUNTRY_ID_KEY_PREFIX + countryId, countryName);
            redisService.setValue(COUNTRY_NAME_KEY_PREFIX + countryName, countryId);
        }

        return countryName;
    }

    @Override
    public List<CountryVO> getAllCountries() {

        List<CountryVO> cachedCountries = redisService.getValue(ALL_LIST_KEY);
        if (cachedCountries != null) {
            return cachedCountries;
        }

        List<Country> countries = countryDao.queryAllCountries();

        List<CountryVO> countryVOs = new ArrayList<>();
        for (Country country : countries) {
            countryVOs.add(CountryVO.builder().
                    countryId(country.getCountryId()).
                    countryName(country.getCountryName()).
                    englishName(country.getEnglishName()).
                    dataCount(countryDao.queryDataCount(country.getCountryId())).
                    build());
        }

        redisService.setValue(ALL_LIST_KEY, countries);

        return countryVOs;
    }

    @Override
    public void insertCountry(CountryVO countryVO) {
        Country country = Country.builder().
                countryId(countryVO.getCountryId()).
                countryName(countryVO.getCountryName()).
                englishName(countryVO.getEnglishName()).
                build();

        redisService.remove(ALL_LIST_KEY);
        countryDao.insertCountry(country);
        redisService.delayedRemove(ALL_LIST_KEY);
    }

    @Override
    public void updateCountry(CountryVO countryVO) {
        Country country = Country.builder().
                countryId(countryVO.getCountryId()).
                countryName(countryVO.getCountryName()).
                englishName(countryVO.getEnglishName()).
                build();

        String oldName = getCountryNameById(countryVO.getCountryId());
        redisService.remove(COUNTRY_ID_KEY_PREFIX + countryVO.getCountryId(), COUNTRY_NAME_KEY_PREFIX + oldName, ALL_LIST_KEY);
        countryDao.updateCountry(country);
        redisService.delayedRemove(COUNTRY_ID_KEY_PREFIX + countryVO.getCountryId(), COUNTRY_NAME_KEY_PREFIX + oldName, ALL_LIST_KEY);
    }

    @Override
    public void deleteCountry(int countryId) {
        String countryName = getCountryNameById(countryId);
        redisService.remove(ALL_LIST_KEY, COUNTRY_ID_KEY_PREFIX + countryId, COUNTRY_NAME_KEY_PREFIX + countryName);
        countryDao.deleteCountry(countryId);
        redisService.delayedRemove(ALL_LIST_KEY, COUNTRY_ID_KEY_PREFIX + countryId, COUNTRY_NAME_KEY_PREFIX + countryName);
    }

    // 只有在录入数据库的时候用到，不需要缓存
    @Override
    public Integer getCountryIdByName(String countryName) {
        return countryDao.queryCountryIdByName(countryName);
    }
}
