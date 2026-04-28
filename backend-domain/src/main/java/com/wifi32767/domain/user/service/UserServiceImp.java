package com.wifi32767.domain.user.service;

import com.wifi32767.domain.system.model.UserRoleVO;
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
    public UserVO getUserInfo(String username) {
        return userRepository.getUserInfo(username);
    }

    @Override
    public List<UserVO> getAllUsersInfo(int pageNum, int pageSize) {
        return userRepository.getAllUsersInfo(pageNum, pageSize);
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteUser(username);
    }

    @Override
    public void updateUserInfo(UserVO user) {
        userRepository.updateUserInfo(user);
    }

    @Override
    public List<UserRoleVO> getAllUsersRole() {
        return userRepository.getAllUsersRole();
    }

    @Override
    public void addUserRole(UserRoleVO userRole) {
        userRepository.addUserRole(userRole);
    }

    @Override
    public void removeUserRole(int userRoleId) {
        userRepository.removeUserRole(userRoleId);
    }

    @Override
    public void addPermission(int userRoleId, int permissionId) {
        userRepository.addPermission(userRoleId, permissionId);
    }

    @Override
    public void addPermissionBatch(int userRoleId, List<Integer> permissionIds) {
        userRepository.addPermissionBatch(userRoleId, permissionIds);
    }

    @Override
    public void removePermission(int userRoleId, int permissionId) {
        userRepository.removePermission(userRoleId, permissionId);
    }

    @Override
    public void removePermissionBatch(int userRoleId, List<Integer> permissionIds) {
        userRepository.removePermissionBatch(userRoleId, permissionIds);
    }

    @Override
    public boolean hasPermission(int userRoleId, int permissionId) {
        return userRepository.hasPermission(userRoleId, permissionId);
    }
}
