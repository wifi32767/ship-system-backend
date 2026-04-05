package com.wifi32767.infra.dao;

import com.wifi32767.infra.dao.po.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    Integer insert(User user);

    User queryByUsername(String username);

    List<User> queryAll();

    void deleteByUsername(String username);
}
