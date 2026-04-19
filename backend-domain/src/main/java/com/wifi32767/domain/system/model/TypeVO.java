package com.wifi32767.domain.system.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TypeVO {
    private Integer typeId; // 三级分类id
    private String typeName; // 三级分类名称
    private String typeDescribe; // 三级分类描述
    private LocalDateTime createTime; // 创建时间
}
