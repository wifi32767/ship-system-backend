package com.wifi32767.domain.system.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassVO {
    private Integer classId; // 一级分类id
    private String className; // 一级分类名称
    private String classDescribe; // 一级分类描述
    private LocalDateTime createTime; // 创建时间
    private List<StyleVO> styleList; // 二级分类列表
}
