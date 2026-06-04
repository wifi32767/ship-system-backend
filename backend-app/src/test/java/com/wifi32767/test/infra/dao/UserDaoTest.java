package com.wifi32767.test.infra.dao;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wifi32767.infra.dao.UserDao;
import com.wifi32767.infra.dao.po.User;
import com.wifi32767.infra.dao.po.UserRole;
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
    public void queryByUsernameTest() {
        String username = "root";
        var user = userDao.queryUserByUsername(username);
        log.info("User details for username '{}': {}", username, user);
    }

    @Test
    public void queryAllUsersTest() {
        var users = userDao.queryAllUsers();
        log.info("all users: {}", users);
    }

    @Test
    public void queryAllUsersTest2() {
        Page<User> page = new Page<>(1, 10);
        var users = userDao.queryAllUsers(page);
        log.info("all users: {}", users.getRecords());
    }

    @Test
    public void insertUserRoleTest() {
        UserRole userRole = new UserRole();
        userRole.setRoleName("testttt");
        userDao.insertUserRole(userRole);
        log.info("insert user role: {}", userRole.getRoleId());
    }

    @Test
    public void encoderTest() {
        var encoder = new BCryptPasswordEncoder(10);
        String pwd = encoder.encode("asdasd");
        log.info("{}", pwd);
        log.info("match: {}", encoder.matches("asdasd", pwd));
    }
}
