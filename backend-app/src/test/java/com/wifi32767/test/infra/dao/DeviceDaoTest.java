package com.wifi32767.test.infra.dao;

import com.wifi32767.infra.dao.DeviceDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@Slf4j
@SpringBootTest(classes = com.wifi32767.app.Application.class)
public class DeviceDaoTest {
    @Autowired
    private DeviceDao deviceDao;
    @Test
    public void queryAllCountTest() {
        log.info("测试开始");
        int cnt = deviceDao.queryAllCount();
        log.info("测试结果:{}", cnt);
    }

    @Test
    public void queryCountByDateTest() {
        log.info("测试开始");
        Integer cnt = deviceDao.queryCountByDate(LocalDate.now().minusDays(7));
        log.info("测试结果:{}", cnt);
    }

}
