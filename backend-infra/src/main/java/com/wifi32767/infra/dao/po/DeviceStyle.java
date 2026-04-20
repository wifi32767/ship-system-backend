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
public class DeviceStyle {
    /**
     * 分类ID
     */
    private Integer deviceStyleId;
    /**
     * 分类名称
     */
    private String deviceStyleName;
    /**
     * 分类描述
     */
    private String deviceStyleDescribe;
    /**
     * 分类所属父分类ID
     */
    private Integer deviceStyleClassId;
    /**
     * 分类创建时间
     */
    private LocalDateTime deviceStyleInsqlTime;
    /**
     * 分类修改时间
     */
    private LocalDateTime deviceStyleChangesqlTime;
}
