package com.wifi32767.test.infra.adapter.client;

import com.wifi32767.interfaces.client.SpiderClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(classes = com.wifi32767.app.Application.class)
public class SpiderClientTest {
    @Autowired
    private SpiderClient spiderClient;

    @Test
    public void getModelListTest() {
        log.info("{}", spiderClient.getModelList());
    }

    @Test
    public void getModelDetailTest() {
        log.info("{}", spiderClient.getModelDetail(1));
    }
}
