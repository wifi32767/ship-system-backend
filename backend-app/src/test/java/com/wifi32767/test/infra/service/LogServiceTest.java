package com.wifi32767.test.infra.service;

import com.wifi32767.domain.system.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest(classes = com.wifi32767.app.Application.class)
public class LogServiceTest {

    @Resource
    private LogService logService;

    @Test
    public void getLogsTest() {
        logService.getLogs(1, 100).forEach(
                logVO -> log.info("{}\n", logVO)
        );
    }
}
