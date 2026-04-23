package com.wifi32767.domain.system.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FullDeviceVO {

    /**
     * id、表id
     */
    private Long id;

    /**
     * 名称
     */
    private String deviceName;

    /**
     * 分类
     */
    private KeyValue deviceClass;

    /**
     * 子分类
     */
    private KeyValue deviceStyle;

    /**
     * 具体方向
     */
    private KeyValue deviceType;

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
     * 所属国家
     */
    private KeyValue deviceCountry;

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

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    static public class KeyValue {
        private int id;
        private String name;
    }
}
