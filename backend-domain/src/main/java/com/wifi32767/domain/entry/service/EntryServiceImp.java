package com.wifi32767.domain.entry.service;

import com.wifi32767.domain.entry.adapter.repository.EntryRepository;
import com.wifi32767.domain.portal.model.DeviceVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EntryServiceImp implements EntryService {

    @Resource
    private EntryRepository entryRepository;

    @Override
    public void single(DeviceVO device) {
        entryRepository.single(device);
    }
}
