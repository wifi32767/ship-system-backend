package com.wifi32767.infra.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClassDao {
    String queryClassNameById(Integer classId);
}
