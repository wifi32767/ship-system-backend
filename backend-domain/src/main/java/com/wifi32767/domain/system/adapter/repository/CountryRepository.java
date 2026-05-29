package com.wifi32767.domain.system.adapter.repository;

import com.wifi32767.domain.system.model.CountryEntity;

import java.util.List;

public interface CountryRepository {
    String getCountryNameById(int countryId);

    List<CountryEntity> getAllCountries();

    void insertCountry(CountryEntity countryEntity);

    void updateCountry(CountryEntity countryEntity);

    void deleteCountry(int countryId);

    Integer getCountryIdByName(String countryName);
}
