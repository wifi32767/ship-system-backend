package com.wifi32767.domain.system.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleVO {
    private Integer roleId; // 角色id
    private String roleName; // 角色名称
    private List<ModuleVO> modules; // 有权限的板块
}
