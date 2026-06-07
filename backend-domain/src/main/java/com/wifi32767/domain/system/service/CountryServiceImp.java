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
        if (country == null || country.getCountryName() == null) {
            return;
        }
        countryRepository.insertCountry(country);
    }


    @Override
    public void deleteCountry(Integer id) {
        if (id == null) {
            return;
        }
        countryRepository.deleteCountry(id);
    }

    @Override
    public void deleteCountries(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        for (Integer id : ids) {
            countryRepository.deleteCountry(id);
        }
    }


    @Override
    public void updateCountry(CountryEntity country) {
        if (country == null || country.getCountryId() == null) {
            return;
        }
        countryRepository.updateCountry(country);
    }
}
