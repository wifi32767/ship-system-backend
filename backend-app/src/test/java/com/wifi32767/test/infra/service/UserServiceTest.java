package com.wifi32767.test.infra.service;

import com.wifi32767.domain.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest(classes = com.wifi32767.app.Application.class)
public class UserServiceTest {
    @Resource
    private UserService userService;

    @Test
    public void getUserInfoTest() {
        log.info("{}", userService.getUserInfo("root"));
    }
}
