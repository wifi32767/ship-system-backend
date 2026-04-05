package com.wifi32767.domain.user.service;

import com.wifi32767.domain.user.adapter.repository.UserRepository;
import com.wifi32767.domain.user.model.SimpleUserVO;
import com.wifi32767.domain.user.model.UserVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImp implements UserService {
    @Resource
    private UserRepository userRepository;

    @Override
    public int register(UserVO user) throws Exception {
        return userRepository.register(user);
    }

    @Override
    public String login(String username, String password) throws Exception {
        int userId = userRepository.login(username, password);
        if (userId == 0) {
            return null;
        }
        UUID randomUUID = UUID.randomUUID();
        return randomUUID.toString();
    }

    @Override
    public void delete(String username) {
        userRepository.delete(username);
    }

    @Override
    public UserVO getUserInfo(String username) {
        return userRepository.getUserInfo(username);
    }

    @Override
    public SimpleUserVO getSimpleUserInfo(String username) {
        return userRepository.getSimpleUserInfo(username);
    }

    @Override
    public List<SimpleUserVO> getAllUsersInfo() {
        // TODO: 分页机制
        return userRepository.getAllUsersInfo();
    }
}
