package com.wifi32767.infra.dao;

import com.wifi32767.infra.dao.po.User;
import com.wifi32767.infra.dao.po.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    Integer insertUser(User user);

    User queryUserByUsername(String username);

    List<User> queryAllUsers(int offset, int pageSize);

    void deleteUserByUsername(String username);

    void updateUserInfo(User user);

    List<UserRole> queryAllUsersRole();

    String queryRoleNameByRoleId(int roleId);

    void insertUserRole(UserRole userRole);

    void deleteUserRole(int roleId);

    List<Integer> queryPermissionsByRoleId(int roleId);

    void insertPermission(int roleId, int permissionId);

    void deletePermission(int roleId, int permissionId);

    boolean hasPermission(int roleId, int permissionId);
}
