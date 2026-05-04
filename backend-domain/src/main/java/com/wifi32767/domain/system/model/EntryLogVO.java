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
public class EntryLogVO {
    /**
     * csv导入日志id
     */
    private Integer id;

    /**
     * 模型日志id，生成的csv的标识
     */
    private Integer modelLogId;

    /**
     * csv导入日志
     */
    private String csvEnterLogs;

    /**
     * 数据量
     */
    private Integer csvEnterNumber;

    /**
     * 导入成功数量
     */
    private Integer csvEnterSuccessNumber;

    /**
     * 日志时间
     */
    private LocalDateTime csvEnterLogsTime;

    /**
     * 导入人员名
     */
    private String csvEnterUserName;
}
