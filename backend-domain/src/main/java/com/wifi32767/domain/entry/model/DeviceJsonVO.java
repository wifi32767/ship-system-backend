package com.wifi32767.domain.entry.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceJsonVO {

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
    private String deviceClassName;

    /**
     * 子分类
     */
    private String deviceStyleName;

    /**
     * 具体方向
     */
    private String deviceTypeName;

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
    private String deviceCountryName;

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
}
