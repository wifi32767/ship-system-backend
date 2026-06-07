package com.wifi32767.domain.user.service;

import com.wifi32767.domain.system.model.UserRoleEntity;
import com.wifi32767.domain.user.adapter.repository.UserRepository;
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
        if (user == null || user.getUserRole() == null || user.getUserName() == null || user.getNickName() == null) {
            return -1;
        }
        return userRepository.register(user);
    }

    @Override
    public String login(String username, String password) throws Exception {
        if (username == null) {
            return null;
        }
        int userId = userRepository.login(username, password);
        if (userId == 0) {
            return null;
        }
        UUID randomUUID = UUID.randomUUID();
        return randomUUID.toString();
    }

    @Override
    public UserVO getUserInfo(String username) {
        if (username == null) {
            return null;
        }
        return userRepository.getUserInfo(username);
    }

    @Override
    public List<UserVO> getAllUsersInfo(int pageNum, int pageSize) {
        if (pageNum <= 0 || pageSize <= 0) {
            return null;
        }
        return userRepository.getAllUsersInfo(pageNum, pageSize);
    }

    @Override
    public void deleteUser(String username) {
        if (username == null) {
            return;
        }
        userRepository.deleteUser(username);
    }

    @Override
    public void updateUserInfo(UserVO user) {
        if (user == null || user.getUserId() == null) {
            return;
        }
        userRepository.updateUserInfo(user);
    }

    @Override
    public List<UserRoleEntity> getAllUsersRole() {
        return userRepository.getAllUsersRole();
    }

    @Override
    public void addUserRole(UserRoleEntity userRole) {
        if (userRole == null || userRole.getRoleId() == null || userRole.getRoleName() == null) {
            return;
        }
        userRepository.addUserRole(userRole);
    }

    @Override
    public void removeUserRole(int userRoleId) {
        if (userRoleId <= 0) {
            return;
        }
        userRepository.removeUserRole(userRoleId);
    }

    @Override
    public void addPermission(int userRoleId, int permissionId) {
        if (userRoleId <= 0 || permissionId <= 0 || permissionId > 9) {
            return;
        }
        userRepository.addPermission(userRoleId, permissionId);
    }

    @Override
    public void addPermissionBatch(int userRoleId, List<Integer> permissionIds) {
        userRepository.addPermissionBatch(userRoleId, permissionIds);
    }

    @Override
    public void removePermission(int userRoleId, int permissionId) {
        if (userRoleId <= 0 || permissionId <= 0 || permissionId > 9) {
            return;
        }
        userRepository.removePermission(userRoleId, permissionId);
    }

    @Override
    public void removePermissionBatch(int userRoleId, List<Integer> permissionIds) {
        userRepository.removePermissionBatch(userRoleId, permissionIds);
    }

    @Override
    public boolean hasPermission(int userRoleId, int permissionId) {
        if (userRoleId <= 0 || permissionId <= 0 || permissionId > 9) {
            return false;
        }
        return userRepository.hasPermission(userRoleId, permissionId);
    }
}
