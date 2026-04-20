package com.wifi32767.domain.system.service;

import com.wifi32767.domain.system.model.CountryVO;

import java.util.List;

public interface CountryService {
    List<CountryVO> getCountries();
    void insertCountry(CountryVO country);
    void deleteCountry(Integer id);
    void deleteCountries(List<Integer> ids);
    void updateCountry(CountryVO country);
}
