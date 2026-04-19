package com.wifi32767.domain.entry.service;

import com.wifi32767.domain.entry.adapter.repository.EntryRepository;
import com.wifi32767.domain.portal.model.DeviceVO;
import org.springframework.stereotype.Service;

@Service
public class EntryServiceImp implements EntryService {

    //    @Resource
    private EntryRepository entryRepository;

    @Override
    public String single(DeviceVO device) {
        return entryRepository.single(device);
    }
}
