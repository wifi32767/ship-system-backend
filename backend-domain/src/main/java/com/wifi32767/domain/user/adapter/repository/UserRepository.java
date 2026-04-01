package com.wifi32767.domain.user.adapter.repository;

import com.wifi32767.domain.user.model.SimpleUserVO;
import com.wifi32767.domain.user.model.UserVO;

import java.util.List;

public interface UserRepository {
    int register(UserVO user) throws Exception;

    int login(String username, String password) throws Exception;

    void delete(String username);

    UserVO getUserInfo(String username);

    List<SimpleUserVO> getAllUsersInfo();
}
