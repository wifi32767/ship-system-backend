package com.wifi32767.test.infra.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wifi32767.infra.dao.LogDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest(classes = com.wifi32767.app.Application.class)
public class LogDaoTest {
    @Resource
    private LogDao logDao;

    @Test
    public void queryAllLogsWithPageTest() {
        logDao.queryAllLogsPage(new Page<>(1, 10)).getRecords().forEach(
                logd -> log.info("log: {}", logd)
        );
    }
}
