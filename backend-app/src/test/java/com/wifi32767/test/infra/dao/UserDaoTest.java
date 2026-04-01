package com.wifi32767.test.infra.dao;


import com.wifi32767.infra.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest(classes = com.wifi32767.app.Application.class)
public class UserDaoTest {
    @Resource
    private UserDao userDao;

    @Test
    public void testQueryByUsername() {
        String username = "root";
        var user = userDao.queryByUsername(username);
        log.info("User details for username '{}': {}", username, user);
    }

    @Test
    public void test() {
        var encoder = new BCryptPasswordEncoder(10);
        String pwd = encoder.encode("asdasd");
        log.info("{}", pwd);
        log.info("match: {}", encoder.matches("asdasd", pwd));
    }

    @Test
    public void testQueryRoleIdByRoleName() {
        String roleName = "admin";
        int roleId = userDao.queryRoleIdByRoleName(roleName);
        log.info("Role ID for role name '{}': {}", roleName, roleId);
    }
}
