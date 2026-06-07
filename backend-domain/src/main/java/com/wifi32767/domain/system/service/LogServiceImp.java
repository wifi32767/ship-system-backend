package com.wifi32767.domain.system.service;

import com.wifi32767.domain.system.adapter.repository.LogRepository;
import com.wifi32767.domain.system.model.EntryLogVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LogServiceImp implements LogService {

    @Resource
    private LogRepository logRepository;

    @Override
    public List<EntryLogVO> getLogs(int pageNum, int pageSize) {
        if (pageNum <= 0 || pageSize <= 0) {
            return null;
        }
        return logRepository.getLogs(pageNum, pageSize);
    }

    @Override
    public void deleteLog(int id) {
        if (id <= 0) {
            return;
        }
        logRepository.deleteLog(id);
    }

    @Override
    public void deleteLogsBatch(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        logRepository.deleteLogsBatch(ids);
    }
}
