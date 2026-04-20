package com.wifi32767.domain.system.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuditSearchParamsVO {
    Integer id;
    String title; // 标题检索
    Integer status; // 状态检索 0/null 未审核 1审核通过 2审核未通过
}
