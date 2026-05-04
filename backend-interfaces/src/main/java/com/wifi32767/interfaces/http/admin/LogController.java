package com.wifi32767.interfaces.http.admin;

import com.wifi32767.domain.system.model.EntryLogVO;
import com.wifi32767.interfaces.common.Response;

import java.util.List;

public interface LogController {
    /**
     * 获取日志列表接口：返回所有日志信息。
     * 数据格式：JSON
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return 日志信息列表 {@code List<EntryLogVO>}
     */
    Response<List<EntryLogVO>> getLogs(int pageNum, int pageSize);

    /**
     * 删除日志接口：删除指定日志。
     * 数据格式：JSON
     *
     * @param id 日志ID
     * @return 删除结果
     */
    Response<String> deleteLog(int id);

    /**
     * 批量删除日志接口：批量删除指定日志。
     * 数据格式：JSON
     *
     * @param ids 日志ID列表
     * @return 删除结果
     */
    Response<String> deleteLogsBatch(List<Integer> ids);

    // TODO: 根据日期清空日志
}
