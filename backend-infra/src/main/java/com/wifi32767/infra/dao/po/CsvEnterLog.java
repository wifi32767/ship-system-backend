package com.wifi32767.infra.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * csv导入日志表
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@TableName("csv_enter_logs")
public class CsvEnterLog {
    /**
     * csv导入日志id
     */
    @TableId(type = IdType.AUTO)
    private Integer csvEnterLogsId;

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
    private java.time.LocalDateTime csvEnterLogsTime;

    /**
     * 导入人员id
     */
    private Integer csvEnterUserId;

    /**
     *
     */
    private String csvEnterState;

    /**
     *
     */
    private String csvAddress;

    /**
     *
     */
    private String runState;

    /**
     * 逻辑删除
     */
    private Integer deleted;
}
