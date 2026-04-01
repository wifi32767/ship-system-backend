package com.wifi32767.domain.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 用户角色
     */
    private String userRole;
    /**
     * 用户账号
     */
    private String userName;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户性别
     */
    private String sex;
    /**
     * 头像地址
     */
    private String avatarAddress;
    /**
     * 密码（存储时应加密）
     */
    private String password;
    /**
     * 备注
     */
    private String remark;
    /**
     * 手机号码
     */
    private String phoneNumber;
}
