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
public class StyleVO {
    private Integer styleId; // 二级分类id
    private String styleName; // 二级分类名称
    private String styleDescribe; // 二级分类描述
    private LocalDateTime createTime; // 创建时间
    private List<TypeVO> typeList; // 三级分类列表
}
