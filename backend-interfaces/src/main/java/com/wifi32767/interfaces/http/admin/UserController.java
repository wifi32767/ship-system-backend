package com.wifi32767.interfaces.http.admin;

import com.wifi32767.domain.system.model.UserRoleVO;
import com.wifi32767.domain.common.enums.Module;
import com.wifi32767.domain.user.model.UserVO;
import com.wifi32767.interfaces.common.Response;

import java.util.List;

public interface UserController {
    /**
     * 登录接口：验证用户凭证并返回访问令牌（token）。
     * 数据格式：JSON
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录结果，成功时返回 token {@code String}
     */
    Response<String> login(String username, String password);

    /**
     * 登出接口：使指定的登录令牌失效。
     * 数据格式：JSON
     *
     * @param token 用户登录令牌
     * @return 登出操作结果消息 {@code String}
     */
    Response<String> logout(String token);

    /**
     * 注册接口：创建新用户，用户名应唯一。
     * 数据格式：JSON
     *
     * @param user 注册用户信息 DTO {@link UserVO}
     * @return 注册结果，成功时返回新用户 ID {@code Integer}
     */
    Response<Integer> register(UserVO user);

    /**
     * 获取用户信息：返回用户信息列表（表示所有用户）。
     * 数据格式：JSON
     *
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @return 用户信息列表 {@code List<UserVO>}
     */
    Response<List<UserVO>> getAllUsersInfo(int pageNum, int pageSize);

    /**
     * 删除用户接口：根据用户名删除用户记录。
     * 数据格式：JSON
     *
     * @param username 目标用户名
     * @return 删除操作结果消息 {@code String}
     */
    Response<String> deleteUser(String username);

    /**
     * 修改用户信息接口：根据用户名修改用户信息。
     * 数据格式：JSON
     *
     * @param user 修改用户信息 DTO {@link UserVO}
     * @return 修改操作结果消息 {@code String}
     */
    Response<String> updateUserInfo(UserVO user);

    /**
     * 获取用户角色接口：返回用户角色列表（表示所有用户角色）。
     * 数据格式：JSON
     *
     * @return 用户角色列表 {@code List<UserRoleVO>}
     */
    Response<List<UserRoleVO>> getAllUsersRole();

    /**
     * 添加用户角色接口：添加用户角色。
     * 数据格式：JSON
     *
     * @param userRoleVO 添加用户角色 DTO {@link UserRoleVO}
     * @return 添加操作结果消息 {@code String}
     */
    Response<String> addUserRole(UserRoleVO userRoleVO);

    /**
     * 删除用户角色接口：删除用户角色。
     * 数据格式：JSON
     *
     * @param roleId 删除用户角色 ID
     * @return 删除操作结果消息 {@code String}
     */
    Response<String> removeUserRole(int roleId);

    /**
     * 获取用户权限接口：返回用户权限列表（表示所有用户权限）。
     * 数据格式：JSON
     *
     * @return 用户权限列表 {@code List<Module>}
     */
    Response<List<Module>> getAllModules();

    /**
     * 添加用户权限接口：添加用户权限。
     * 数据格式：JSON
     *
     * @param roleId 添加用户权限角色 ID
     * @param permissionId 添加用户权限
     * @return 添加操作结果消息 {@code String}
     */
    Response<String> addPermission(int roleId, int permissionId);

    /**
     * 批量添加用户权限接口：批量添加用户权限。
     * 数据格式：JSON
     *
     * @param roleId 添加用户权限角色 ID
     * @param permissionIds 添加用户权限
     * @return 添加操作结果消息 {@code String}
     */
    Response<String> addPermissionBatch(int roleId, List<Integer> permissionIds);

    /**
     * 删除用户权限接口：删除用户权限。
     * 数据格式：JSON
     *
     * @param roleId 删除用户权限角色 ID
     * @param permissionId 删除用户权限
     * @return 删除操作结果消息 {@code String}
     */
    Response<String> removePermission(int roleId, int permissionId);

    /**
     * 批量删除用户权限接口：批量删除用户权限。
     * 数据格式：JSON
     *
     * @param roleId 删除用户权限角色 ID
     * @param permissionIds 删除用户权限
     * @return 删除操作结果消息 {@code String}
     */
    Response<String> removePermissionBatch(int roleId, List<Integer> permissionIds);
}
