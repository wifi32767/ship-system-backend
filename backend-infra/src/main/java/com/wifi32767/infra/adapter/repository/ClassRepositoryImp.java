package com.wifi32767.infra.adapter.repository;

import com.wifi32767.domain.portal.adapter.repository.ClassRepository;
import com.wifi32767.infra.dao.ClassDao;
import com.wifi32767.infra.redis.RedisService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class ClassRepositoryImp implements ClassRepository {

    private static final String CLASS_ID_KEY_PREFIX = "class_id:";
    @Resource
    private ClassDao classDao;
    @Resource
    private RedisService redisService;

    @Override
    public String getClassNameById(int classId) {
        if (redisService.isExists(CLASS_ID_KEY_PREFIX + classId)) {
            return redisService.getValue(CLASS_ID_KEY_PREFIX + classId);
        }
        String className = classDao.queryClassNameById(classId);
        if (className != null) {
            redisService.setValue(CLASS_ID_KEY_PREFIX + classId, className);
        }
        return className;
    }
}
