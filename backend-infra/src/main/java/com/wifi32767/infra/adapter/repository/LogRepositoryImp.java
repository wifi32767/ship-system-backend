package com.wifi32767.infra.adapter.repository;

import com.wifi32767.domain.system.adapter.repository.LogRepository;
import com.wifi32767.domain.system.model.EntryLogVO;
import com.wifi32767.infra.dao.LogDao;
import com.wifi32767.infra.dao.UserDao;
import com.wifi32767.infra.dao.po.CsvEnterLog;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class LogRepositoryImp implements LogRepository {

    @Resource
    private LogDao logDao;

    @Resource
    private UserDao userDao;

    @Override
    public void insertLog(EntryLogVO entryLogVO) {
        logDao.insertLog(
                CsvEnterLog.builder()
                        .csvEnterLogs(entryLogVO.getCsvEnterLogs())
                        .csvEnterNumber(entryLogVO.getCsvEnterNumber())
                        .csvEnterSuccessNumber(entryLogVO.getCsvEnterSuccessNumber())
                        .csvEnterLogsTime(entryLogVO.getCsvEnterLogsTime())
                        .csvEnterUserId(Integer.valueOf(entryLogVO.getCsvEnterUserName()))
                        .modelLogId(entryLogVO.getModelLogId())
                        .build()
        );
    }

    @Override
    public List<EntryLogVO> getLogs(int pageNum, int pageSize) {
//        return logDao.queryAllLogs().stream().map(
//                    log -> log2logVO(log)
//                ).toList();
        return logDao.queryAllLogsWithPage((pageNum - 1) * pageSize, pageSize).stream().map(
                log -> log2logVO(log)
        ).toList();
    }

    @Override
    public void deleteLog(int logId) {
        logDao.deleteLog(logId);
    }

    @Override
    public void deleteLogsBatch(List<Integer> ids) {
        ids.forEach(id -> logDao.deleteLog(id));
    }

    private EntryLogVO log2logVO(CsvEnterLog log) {
        return EntryLogVO.builder()
                .id(log.getCsvEnterLogsId())
                .modelLogId(log.getModelLogId())
                .csvEnterLogs(log.getCsvEnterLogs())
                .csvEnterNumber(log.getCsvEnterNumber())
                .csvEnterSuccessNumber(log.getCsvEnterSuccessNumber())
                .csvEnterLogsTime(log.getCsvEnterLogsTime())
                .csvEnterUserName(userDao.queryUserNameByUserId(log.getCsvEnterUserId()))
                .build();
    }

}
