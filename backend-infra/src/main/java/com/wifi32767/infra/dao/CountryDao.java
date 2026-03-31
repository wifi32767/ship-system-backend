package com.wifi32767.infra.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CountryDao {
    int queryCountryIdByName(String countryName);

    String queryCountryNameById(int countryId);
}
