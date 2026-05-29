package com.wifi32767.domain.user.service;

import com.wifi32767.domain.system.model.UserRoleEntity;
import com.wifi32767.domain.user.model.UserVO;

import java.util.List;

public interface UserService {

    int register(UserVO user) throws Exception;

    String login(String username, String password) throws Exception;

    UserVO getUserInfo(String username);

    List<UserVO> getAllUsersInfo(int pageNum, int pageSize);

    void deleteUser(String username);

    void updateUserInfo(UserVO user);

    List<UserRoleEntity> getAllUsersRole();

    void addUserRole(UserRoleEntity userRole);

    void removeUserRole(int userRoleId);

    void addPermission(int userRoleId, int permissionId);

    void addPermissionBatch(int userRoleId, List<Integer> permissionIds);

    void removePermission(int userRoleId, int permissionId);

    void removePermissionBatch(int userRoleId, List<Integer> permissionIds);

    boolean hasPermission(int userRoleId, int permissionId);
}
