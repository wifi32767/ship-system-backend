package com.wifi32767.infra.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 新闻、事件信息表
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Device {

    /**
     * id、表id
     */
    private Long deviceId;

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
     * 相关新闻信息链接
     */
    private String deviceNewsLink;

    /**
     * 相关新闻信息标题
     */
    private String deviceNewsTitle;

    /**
     * 新闻时间
     */
    private LocalDateTime deviceNewsTime;

    /**
     * 信息入库时间
     */
    private LocalDateTime deviceInsqlTime;

    /**
     * 信息修改时间
     */
    private LocalDateTime deviceChangesqlTime;

    /**
     * 信息审核与否 0未审核 空也未审核 1已审核
     */
    private Integer auditFlag;

    /**
     * 信息逻辑删除字段
     */
    private Integer deleted;
}