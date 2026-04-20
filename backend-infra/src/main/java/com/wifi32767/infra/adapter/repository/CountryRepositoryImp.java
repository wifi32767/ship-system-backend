package com.wifi32767.infra.adapter.repository;

import com.wifi32767.domain.portal.adapter.repository.CountryRepository;
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
    private static final String ALL_LIST_KEY = "country_all_list";
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

    @Override
    public List<CountryVO> getAllCountries() {

        List<CountryVO> cachedCountries = redisService.getValue(ALL_LIST_KEY);
        if (cachedCountries != null && !cachedCountries.isEmpty()) {
            return cachedCountries;
        }

        List<Country> countries = countryDao.queryAllCountries();

        if (countries != null && !countries.isEmpty()) {
            redisService.setValue(ALL_LIST_KEY, countries);
        }

        List<CountryVO> countryVOs = new ArrayList<>();
        for (Country country : countries) {
            countryVOs.add(CountryVO.builder().
                    countryId(country.getCountryId()).
                    countryName(country.getCountryName()).
                    englishName(country.getEnglishName()).
                    dataCount(countryDao.queryDataCount(country.getCountryId())).
                    build());
        }

        return countryVOs;
    }

    @Override
    public void insertCountry(CountryVO countryVO) {
        Country country = Country.builder().
                countryId(countryVO.getCountryId()).
                countryName(countryVO.getCountryName()).
                englishName(countryVO.getEnglishName()).
                build();

        countryDao.insertCountry(country);
        redisService.remove(ALL_LIST_KEY); // 删除缓存
    }

    @Override
    public void updateCountry(CountryVO countryVO) {
        Country country = Country.builder().
                countryId(countryVO.getCountryId()).
                countryName(countryVO.getCountryName()).
                englishName(countryVO.getEnglishName()).
                build();

        countryDao.updateCountry(country);
        redisService.remove(COUNTRY_ID_KEY_PREFIX + countryVO.getCountryId());
        redisService.remove(ALL_LIST_KEY);
    }

    @Override
    public void deleteCountry(int countryId) {
        countryDao.deleteCountry(countryId);
        redisService.remove(ALL_LIST_KEY);
    }
}
