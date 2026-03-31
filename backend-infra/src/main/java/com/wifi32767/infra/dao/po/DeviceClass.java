package com.wifi32767.infra.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceClass {
    /**
     * 分类ID
     */
    private Integer deviceClassId;
    /**
     * 分类名称
     */
    private String deviceClassName;
    /**
     * 分类描述
     */
    private String deviceClassDescribe;
    /**
     * 分类创建时间
     */
    private LocalDateTime deviceClassInsqlTime;
    /**
     * 分类修改时间
     */
    private LocalDateTime deviceClassChangesqlTime;
}
