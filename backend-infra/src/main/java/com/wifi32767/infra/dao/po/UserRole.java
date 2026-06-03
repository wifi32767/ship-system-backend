package com.wifi32767.infra.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@TableName("user_role")
public class UserRole {
    /**
     * 角色id
     */
    @TableId(type = IdType.AUTO)
    private Integer roleId;
    /**
     * 角色名
     */
    private String roleName;
}