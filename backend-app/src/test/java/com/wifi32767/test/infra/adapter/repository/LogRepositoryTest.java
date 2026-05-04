package com.wifi32767.test.infra.adapter.repository;


import com.wifi32767.domain.system.adapter.repository.LogRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest(classes = com.wifi32767.app.Application.class)
public class LogRepositoryTest {
    @Resource
    private LogRepository logRepository;

    @Test
    public void getLogsTest() {
        logRepository.getLogs(1, 100).forEach(
                logVO -> log.info(logVO.toString())
        );
    }
}
