package com.wifi32767.infra.adapter.repository;

import com.wifi32767.domain.portal.adapter.repository.ClassRepository;
import com.wifi32767.infra.dao.ClassDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class ClassRepositoryImp implements ClassRepository {

    @Resource
    private ClassDao classDao;

    @Override
    public String getClassNameById(int classId) {
        return classDao.queryClassNameById(classId);
    }
}
