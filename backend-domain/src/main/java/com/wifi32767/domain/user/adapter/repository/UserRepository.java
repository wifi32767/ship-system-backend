package com.wifi32767.domain.user.adapter.repository;

import com.wifi32767.domain.system.model.UserRoleVO;
import com.wifi32767.domain.user.model.SimpleUserVO;
import com.wifi32767.domain.user.model.UserVO;

import java.util.List;

public interface UserRepository {
    int register(UserVO user) throws Exception;

    int login(String username, String password) throws Exception;

    UserVO getUserInfo(String username);

    List<UserVO> getAllUsersInfo(int pageNum, int pageSize);

    void deleteUser(String username);

    void updateUserInfo(UserVO user);

    List<UserRoleVO> getAllUsersRole();

    void addUserRole(UserRoleVO userRole);

    void removeUserRole(int userRoleId);

    void addPermission(int userRoleId, int permissionId);

    void addPermissionBatch(int userRoleId, List<Integer> permissionIds);

    void removePermission(int userRoleId, int permissionId);

    void removePermissionBatch(int userRoleId, List<Integer> permissionIds);

    boolean hasPermission(int userRoleId, int permissionId);
}
