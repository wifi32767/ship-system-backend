package com.wifi32767.domain.portal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatsBoardVO {
    private long totalIndexed; // 收录总量
    private long todayIndexed; // 今日收录量
    private long todayCorrected; // 今日校错量
    private long indexdDiff; // 与昨日对比的收录量
    private long correctedDiff; // 与昨日对比的校错量
}
