package com.wifi32767.domain.portal.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceVO {

    /**
     * id、表id
     */
    private Long id;

    /**
     * 名称
     */
    private String deviceName;

    /**
     * 分类id、外键
     */
    private Integer deviceClassId;

    /**
     * 子分类id、外键
     */
    private Integer deviceStyleId;

    /**
     * 具体方向id、外键
     */
    private Integer deviceTypeId;

    /**
     * 投产年份
     */
    private Integer deviceUseYear;

    /**
     * 投入成本
     */
    private String devicePrice;

    /**
     * 单位
     */
    private String deviceUsingUnit;

    /**
     * 所属国家id
     */
    private Integer deviceCountryId;

    /**
     * 具体地址
     */
    private String deviceLocation;

    /**
     * 经度
     */
    private String deviceLongitude;

    /**
     * 纬度
     */
    private String deviceLatitude;

    /**
     * 图片地址
     */
    private String deviceImg;

    /**
     * 视频地址
     */
    private String deviceVideo;

    /**
     * 详情介绍
     */
    private String deviceIntroduce;

    /**
     * 信息入库时间
     */
    private LocalDateTime deviceInsqlTime;

    /**
     * 信息修改时间
     */
    private LocalDateTime deviceChangesqlTime;

    /**
     * 审核状态 0待审核 1审核通过 2审核未通过
     */
    private Integer auditFlag;
}
