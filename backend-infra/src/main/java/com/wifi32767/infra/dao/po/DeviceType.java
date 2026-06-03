package com.wifi32767.infra.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@TableName("device_type")
public class DeviceType {
    /**
     * 分类ID
     */
    @TableId(type = IdType.AUTO)
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
    /**
     * 分类删除状态
     */
    private Integer deleted;
}
