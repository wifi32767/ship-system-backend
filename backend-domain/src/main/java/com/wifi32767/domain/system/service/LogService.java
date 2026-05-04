package com.wifi32767.domain.system.service;

import com.wifi32767.domain.system.model.EntryLogVO;

import java.util.List;

public interface LogService {

    List<EntryLogVO> getLogs(int pageNum, int pageSize);

    void deleteLog(int id);

    void deleteLogsBatch(List<Integer> ids);
}
