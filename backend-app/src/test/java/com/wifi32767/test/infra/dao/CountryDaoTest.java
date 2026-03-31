package com.wifi32767.test.infra.dao;

import com.wifi32767.infra.dao.CountryDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest(classes = com.wifi32767.app.Application.class)
public class CountryDaoTest {
    @Resource
    private CountryDao countryDao;

    @Test
    public void queryCountryIdByNameTest() {
        String countryName = "中国";
        Integer countryId = countryDao.queryCountryIdByName(countryName);
        log.info("countryId: {}", countryId);
    }

    @Test
    public void queryCountryNameByIdTest() {
        int countryId = 2;
        String countryName = countryDao.queryCountryNameById(countryId);
        log.info("countryName: {}", countryName);
    }
}
