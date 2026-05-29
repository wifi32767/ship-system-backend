package com.wifi32767.domain.system.service;

import com.wifi32767.domain.system.adapter.repository.CountryRepository;
import com.wifi32767.domain.system.model.CountryEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CountryServiceImp implements CountryService {

    @Resource
    private CountryRepository countryRepository;

    @Override
    public List<CountryEntity> getCountries() {
        return countryRepository.getAllCountries();
    }

    @Override
    public void insertCountry(CountryEntity country) {
        countryRepository.insertCountry(country);
    }


    @Override
    public void deleteCountry(Integer id) {
        countryRepository.deleteCountry(id);
    }

    @Override
    public void deleteCountries(List<Integer> ids) {
        for (Integer id : ids) {
            countryRepository.deleteCountry(id);
        }
    }


    @Override
    public void updateCountry(CountryEntity country) {
        countryRepository.updateCountry(country);
    }
}
