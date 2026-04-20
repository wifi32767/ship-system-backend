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
public class DeviceType {
    /**
     * 分类ID
     */
    private Integer deviceTypeId;
    /**
     * 分类名称
     */
    private String deviceTypeName;
    /**
     * 分类描述
     */
    private String deviceTypeDescribe;
    /**
     * 分类所属父分类ID
     */
    private Integer deviceTypeStyleId;
    /**
     * 分类创建时间
     */
    private LocalDateTime deviceTypeInsqlTime;
    /**
     * 分类修改时间
     */
    private LocalDateTime deviceTypeChangesqlTime;
}
