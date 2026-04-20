package com.wifi32767.infra.dao;

import org.apache.ibatis.annotations.Mapper;

import com.wifi32767.infra.dao.po.Country;

import java.util.List;

@Mapper
public interface CountryDao {
    int queryCountryIdByName(String countryName);

    String queryCountryNameById(int countryId);

    int queryDataCount(int countryId);

    List<Country> queryAllCountries();

    void insertCountry(Country country);

    void updateCountry(Country country);

    void deleteCountry(int countryId);
}
