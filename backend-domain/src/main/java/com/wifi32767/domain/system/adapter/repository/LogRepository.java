package com.wifi32767.domain.system.adapter.repository;

import com.wifi32767.domain.system.model.EntryLogVO;

import java.util.List;

public interface LogRepository {
    void insertLog(EntryLogVO log);

    List<EntryLogVO> getLogs(int pageNum, int pageSize);

    void deleteLog(int id);

    void deleteLogsBatch(List<Integer> ids);
}
