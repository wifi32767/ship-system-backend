package com.wifi32767.test.infra.adapter.repository;


import com.wifi32767.domain.system.adapter.repository.ClassRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest(classes = com.wifi32767.app.Application.class)
public class ClassRepositoryTest {
    @Resource
    private ClassRepository classRepository;

    @Test
    public void getAllClassesTest() {
        log.info("{}", classRepository.getAllClasses());
    }
}
