package com.wifi32767.domain.portal.service;

import com.wifi32767.domain.portal.adapter.repository.PortalRepository;
import com.wifi32767.domain.portal.model.DeviceVO;
import com.wifi32767.domain.portal.model.NewsVO;
import com.wifi32767.domain.portal.model.StatsBoardVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

@Service
public class PortalServiceImp implements PortalService {
    @Resource
    private PortalRepository repository;

    @Override
    public StatsBoardVO getStatsBoard() {
        StatsBoardVO statsBoardVO = new StatsBoardVO();
        LocalDate today = LocalDate.now();
        statsBoardVO.setTotalIndexed(repository.queryDeviceCount());
        int indexedCount = repository.queryDeviceCountByDate(today);
        int correctionCount = repository.queryDeviceCorrectionCountByDate(today);
        statsBoardVO.setTodayIndexed(indexedCount);
        statsBoardVO.setTodayCorrected(correctionCount);
        indexedCount -= repository.queryDeviceCountByDate(today.minusDays(1));
        correctionCount -= repository.queryDeviceCorrectionCountByDate(today.minusDays(1));
        statsBoardVO.setIndexdDiff(indexedCount);
        statsBoardVO.setCorrectedDiff(correctionCount);
        return statsBoardVO;
    }

    @Override
    public List<NewsVO> getLatestNews(int count) {
        return repository.queryLatestNews(count);
    }

    @Override
    public List<DeviceVO> getLatestDevices(int count) {
        return repository.queryLatestDevices(count);
    }

    @Override
    public List<DeviceVO> getRandomDevices(int count) {
        return repository.queryRandomDevices(count);
    }
}
