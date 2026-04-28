package com.wifi32767.infra.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 角色名
     */
    private String roleName;
}