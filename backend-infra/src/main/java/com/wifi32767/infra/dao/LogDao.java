package com.wifi32767.infra.dao;

import com.wifi32767.infra.dao.po.CsvEnterLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LogDao {

    void insertLog(CsvEnterLog log);

    void deleteLog(int logId);

    List<CsvEnterLog> queryAllLogs();

    List<CsvEnterLog> queryAllLogsWithPage(int offset, int pageSize);
}
