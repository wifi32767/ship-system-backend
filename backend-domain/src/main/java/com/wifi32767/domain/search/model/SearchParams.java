package com.wifi32767.domain.search.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchParams {
    String keyword; // 检索标题
    String introduce; // 检索介绍
    Integer classId;      // 一级分类ID
    Integer styleId;      // 二级分类ID
    Integer typeId;       // 三级分类ID
    Integer countryId; // 国家检索
    LocalDateTime startDate; // 开始时间
    LocalDateTime endDate; // 结束时间
}
