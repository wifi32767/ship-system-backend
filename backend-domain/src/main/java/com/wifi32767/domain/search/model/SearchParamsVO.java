package com.wifi32767.domain.search.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchParamsVO {
    String keyword; // 全文检索
    String title; // 标题检索
    Integer classId;      // 一级分类ID
    Integer styleId;      // 二级分类ID
    Integer typeId;       // 三级分类ID
    String country; // 国家检索
}
