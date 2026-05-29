package com.wifi32767.domain.system.service;

import com.wifi32767.domain.system.model.CountryEntity;

import java.util.List;

public interface CountryService {
    List<CountryEntity> getCountries();

    void insertCountry(CountryEntity country);

    void deleteCountry(Integer id);

    void deleteCountries(List<Integer> ids);

    void updateCountry(CountryEntity country);
}
