package com.wifi32767.interfaces.http.admin;

import com.wifi32767.domain.system.model.ModuleVO;
import com.wifi32767.domain.system.model.UserRoleVO;
import com.wifi32767.interfaces.common.Response;

import java.util.List;

public interface UserRoleController {
    /**
     * 获取板块列表：返回系统中所有的模块信息。
     * 数据格式：JSON
     *
     * @return 模块信息列表 {@code List<ModuleVO>}
     */
    Response<List<ModuleVO>> moduleGet();

    /**
     * 添加板块：创建新的系统模块。
     * 数据格式：JSON
     *
     * @param module 模块名称或标识 {@code String}
     * @return 操作结果消息 {@code String}
     */
    Response<String> moduleInsert(String module);

    /**
     * 修改板块：更新现有模块的信息。
     * 数据格式：JSON
     *
     * @param module 模块详细信息 {@link ModuleVO}
     * @return 操作结果消息 {@code String}
     */
    Response<String> moduleEdit(ModuleVO module);

    /**
     * 获取角色列表：返回系统中所有的用户角色信息。
     * 数据格式：JSON
     *
     * @return 用户角色信息列表 {@code List<UserRoleVO>}
     */
    Response<List<UserRoleVO>> roleGet();

    /**
     * 添加角色：创建新的用户角色。
     * 数据格式：JSON
     *
     * @param role 角色详细信息 {@link UserRoleVO}
     * @return 操作结果消息 {@code String}
     */
    Response<String> roleInsert(UserRoleVO role);

    /**
     * 修改角色：更新现有用户角色的信息。
     * 数据格式：JSON
     *
     * @param role 角色详细信息 {@link UserRoleVO}
     * @return 操作结果消息 {@code String}
     */
    Response<String> roleEdit(UserRoleVO role);
}
