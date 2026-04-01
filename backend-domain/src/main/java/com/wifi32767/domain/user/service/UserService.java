package com.wifi32767.domain.user.service;

import com.wifi32767.domain.user.model.SimpleUserVO;
import com.wifi32767.domain.user.model.UserVO;

import java.util.List;

public interface UserService {

    int register(UserVO user);

    String login(String username, String password);

    void delete(String username);

    UserVO getUserInfo(String username);

    List<SimpleUserVO> getAllUsersInfo();
}
