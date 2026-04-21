package com.wifi32767.test.infra.dao;

import com.wifi32767.infra.dao.ClassDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest(classes = com.wifi32767.app.Application.class)
public class ClassDaoTest {
    @Resource
    private ClassDao classDao;

    @Test
    public void queryClassNameByIdTest() {
        String className = classDao.queryClassNameById(5);
        log.info("className: {}", className);
    }

    @Test
    public void queryStyleNameByIdTest() {
        String styleName = classDao.queryStyleNameById(1);
        log.info("styleName: {}", styleName);
    }

    @Test
    public void queryTypeNameByIdTest() {
        String typeName = classDao.queryTypeNameById(1);
        log.info("typeName: {}", typeName);
    }

    @Test
    public void getAllClassTest() {
        log.info("getAllClass: {}", classDao.getAllClass());
    }

    @Test
    public void queryStyleByClassIdTest() {
        log.info("queryStyleByClassId: {}", classDao.queryStyleByClassId(5));
    }
}
