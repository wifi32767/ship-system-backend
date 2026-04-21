package com.wifi32767.interfaces.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDTO {

    /**
     * id、表id
     */
    private Long id;

    /**
     * 名称
     */
    private String deviceName;

    /**
     * 分类名、外键
     */
    private String deviceClass;

    /**
     * 子分类名、外键
     */
    private String deviceStyle;

    /**
     * 具体方向名、外键
     */
    private String deviceType;

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
     * 所属国家名
     */
    private String deviceCountry;

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
}
