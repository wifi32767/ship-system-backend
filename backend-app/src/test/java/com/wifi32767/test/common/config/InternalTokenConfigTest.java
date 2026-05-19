package com.wifi32767.test.common.config;

import com.wifi32767.common.config.InternalTokenConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(classes = com.wifi32767.app.Application.class)
public class InternalTokenConfigTest {
    @Autowired
    private InternalTokenConfig config;

    @Test
    public void getTokenTest() {
        log.info("{}", config.getToken());
    }
}
