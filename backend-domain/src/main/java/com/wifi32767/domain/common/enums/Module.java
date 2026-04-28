package com.wifi32767.domain.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Module {
    USER(1, "用户管理"), // 用户管理
    ROLE(2, "角色管理"), // 角色管理
    ENTRY(3, "数据录入管理"), // 数据录入管理
    AUDIT(4, "数据审核管理"), // 数据审核管理
    MODIFY(5, "数据修改管理"), // 数据修改管理
    MODEL(6, "模型管理"), // 模型管理
    CATEGORY(7, "分类管理"), // 分类管理
    COUNTRY(8, "国家管理"), // 国家管理
    LOG(9, "日志管理") // 日志管理
    ;

    private final int moduleId; // 模块ID
    private final String moduleName; // 模块名称
}
