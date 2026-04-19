package com.wifi32767.interfaces.http.admin;

import com.wifi32767.domain.user.model.SimpleUserVO;
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
     * 注册接口：创建新用户，用户名应唯一。
     * 数据格式：JSON
     *
     * @param user 注册用户信息 DTO {@link UserVO}
     * @return 注册结果，成功时返回新用户 ID {@code Integer}
     */
    Response<Integer> register(UserVO user);

    /**
     * 登出接口：使指定的登录令牌失效。
     * 数据格式：JSON
     *
     * @param token 用户登录令牌
     * @return 登出操作结果消息 {@code String}
     */
    Response<String> logout(String token);

    /**
     * 获取用户信息：返回用户信息列表（表示所有用户）。
     * 数据格式：JSON
     *
     * @return 用户信息列表 {@code List<SimpleUserVO>}
     */
    Response<List<SimpleUserVO>> getAllUsersInfo();

    /**
     * 删除用户接口：根据用户名删除用户记录。
     * 数据格式：JSON
     *
     * @param username 目标用户名
     * @return 删除操作结果消息 {@code String}
     */
    Response<String> deleteUser(String username);
//    Response<String> updateUserInfo(String username, String password);
}
