package com.wifi32767.domain.user.service;

import com.wifi32767.domain.user.model.SimpleUserVO;
import com.wifi32767.domain.user.model.UserVO;

import java.util.List;

public interface UserService {

    int register(UserVO user) throws Exception;

    String login(String username, String password) throws Exception;

    void delete(String username);

    UserVO getUserInfo(String username);

    List<SimpleUserVO> getAllUsersInfo();
}
