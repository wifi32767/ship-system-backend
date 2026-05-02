package com.wifi32767.test.infra.service;

import com.wifi32767.domain.entry.model.DeviceJsonVO;
import com.wifi32767.domain.entry.service.EntryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest(classes = com.wifi32767.app.Application.class)
public class EntryServiceTest {
    @Resource
    private EntryService entryService;

    @Test
    public void batchTest() {
        log.info("test1");
        List<DeviceJsonVO> deviceList = new ArrayList<>();
        deviceList.add(DeviceJsonVO.builder()
                .deviceName("deviceA")
                .deviceClassName("class1")
                .deviceCountryName("中国")
                .build());
        entryService.batch(deviceList);
    }

    @Test
    public void batchTest2() {
        log.info("test2");
        List<DeviceJsonVO> deviceList = new ArrayList<>();
        deviceList.add(DeviceJsonVO.builder()
                .deviceName("deviceB")
                .deviceClassName("class000")
                .deviceCountryName("invalid")
                .build());
        entryService.batch(deviceList);
    }
}
