package com.wifi32767.domain.system.adapter.repository;

import com.wifi32767.domain.system.model.CountryVO;

import java.util.List;

public interface CountryRepository {
    String getCountryNameById(int countryId);

    List<CountryVO> getAllCountries();

    void insertCountry(CountryVO countryVO);

    void updateCountry(CountryVO countryVO);

    void deleteCountry(int countryId);
}
