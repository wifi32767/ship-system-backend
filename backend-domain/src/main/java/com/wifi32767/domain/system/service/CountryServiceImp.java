package com.wifi32767.domain.system.service;

import com.wifi32767.domain.system.adapter.repository.CountryRepository;
import com.wifi32767.domain.system.model.CountryVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CountryServiceImp implements CountryService {

    @Resource
    private CountryRepository countryRepository;

    @Override
    public List<CountryVO> getCountries() {
        return countryRepository.getAllCountries();
    }

    @Override
    public void insertCountry(CountryVO country) {
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
    public void updateCountry(CountryVO country) {
        countryRepository.updateCountry(country);
    }
}
