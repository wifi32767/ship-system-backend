package com.wifi32767.interfaces.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EchartsOptionDTO {

    private Title title;

    private Tooltip tooltip;

    private Series series;

    private XAxis xAxis;

    private YAxis yAxis;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Title {
        private boolean show = false; // 是否显示标题
        private String text; // 标题文本
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Tooltip {
        private boolean show = false; // 是否显示提示框
        private String trigger; // 触发类型，"item" "axis"
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class XAxis {
        private boolean show = false; // 是否显示X轴
        private String type; // X轴类型，"category" "value" "time"
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class YAxis {
        private boolean show = false; // 是否显示Y轴
        private String type; // Y轴类型，"category" "value" "time"
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Series {
        private String type; // 图表类型，"pie" "line" "bar"
        private String radius; // 饼图半径
        private List data; // 数据项列表
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class KVData {
        private Object name; // 数据项名称
        private Object value; // 数据项数值
    }
}
