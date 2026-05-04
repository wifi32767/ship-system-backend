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
        return logRepository.getLogs(pageNum, pageSize);
    }

    @Override
    public void deleteLog(int id) {
        logRepository.deleteLog(id);
    }

    @Override
    public void deleteLogsBatch(List<Integer> ids) {
        logRepository.deleteLogsBatch(ids);
    }
}
