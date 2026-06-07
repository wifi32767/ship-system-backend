package com.wifi32767.infra.adapter.repository;

import com.wifi32767.domain.system.adapter.repository.ClassRepository;
import com.wifi32767.domain.system.model.ClassDTO;
import com.wifi32767.domain.system.model.ClassEntity;
import com.wifi32767.domain.system.model.StyleEntity;
import com.wifi32767.domain.system.model.TypeEntity;
import com.wifi32767.infra.dao.ClassDao;
import com.wifi32767.infra.dao.po.DeviceClass;
import com.wifi32767.infra.dao.po.DeviceStyle;
import com.wifi32767.infra.dao.po.DeviceType;
import com.wifi32767.infra.redis.RedisService;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClassRepositoryImp implements ClassRepository {

    private static final String ALL_LIST_KEY = "class_all_list";
    private static final String CLASS_ID_KEY_PREFIX = "class_id:";
    private static final String STYLE_ID_KEY_PREFIX = "style_id:";
    private static final String TYPE_ID_KEY_PREFIX = "type_id:";

    @Resource
    private ClassDao classDao;
    @Resource
    private RedisService redisService;

    @Override
    public String getClassNameById(int classId) {
        String className = redisService.getValue(CLASS_ID_KEY_PREFIX + classId);
        if (!StringUtils.isEmpty(className)) {
            return className;
        }
        className = classDao.queryClassNameById(classId);
        if (!StringUtils.isEmpty(className)) {
            redisService.setValue(CLASS_ID_KEY_PREFIX + classId, className);
        }
        return className;
    }

    @Override
    public String getStyleNameById(int styleId) {
        String styleName = redisService.getValue(STYLE_ID_KEY_PREFIX + styleId);
        if (!StringUtils.isEmpty(styleName)) {
            return styleName;
        }
        styleName = classDao.queryStyleNameById(styleId);
        if (!StringUtils.isEmpty(styleName)) {
            redisService.setValue(STYLE_ID_KEY_PREFIX + styleId, styleName);
        }
        return styleName;
    }

    @Override
    public String getTypeNameById(int typeId) {
        String typeName = redisService.getValue(TYPE_ID_KEY_PREFIX + typeId);
        if (!StringUtils.isEmpty(typeName)) {
            return typeName;
        }
        typeName = classDao.queryTypeNameById(typeId);
        if (!StringUtils.isEmpty(typeName)) {
            redisService.setValue(TYPE_ID_KEY_PREFIX + typeId, typeName);
        }
        return typeName;
    }

    @Override
    public List<ClassEntity> getAllClasses() {
        List<ClassEntity> classes = redisService.getValue(ALL_LIST_KEY);
        if (classes != null) {
            return classes;
        }

        List<DeviceClass> deviceClasses = classDao.getAllClass();
        classes = new ArrayList<>();

        // 如果有扩展层数的需求，可以用dfs责任链方便维护
        for (DeviceClass deviceClass : deviceClasses) {
            ClassEntity classEntity = new ClassEntity();
            classEntity.setClassId(deviceClass.getDeviceClassId());
            classEntity.setClassName(deviceClass.getDeviceClassName());
            classEntity.setClassDescribe(deviceClass.getDeviceClassDescribe());
            classEntity.setCreateTime(deviceClass.getDeviceClassInsqlTime());

            List<DeviceStyle> deviceStyles = classDao.queryStyleByClassId(deviceClass.getDeviceClassId());
            List<StyleEntity> styles = new ArrayList<>();
            for (DeviceStyle deviceStyle : deviceStyles) {
                StyleEntity styleEntity = new StyleEntity();
                styleEntity.setStyleId(deviceStyle.getDeviceStyleId());
                styleEntity.setStyleName(deviceStyle.getDeviceStyleName());
                styleEntity.setStyleDescribe(deviceStyle.getDeviceStyleDescribe());
                styleEntity.setCreateTime(deviceStyle.getDeviceStyleInsqlTime());

                List<DeviceType> deviceTypes = classDao.queryTypeByStyleId(deviceStyle.getDeviceStyleId());
                List<TypeEntity> types = new ArrayList<>();
                for (DeviceType deviceType : deviceTypes) {
                    TypeEntity typeEntity = new TypeEntity();
                    typeEntity.setTypeId(deviceType.getDeviceTypeId());
                    typeEntity.setTypeName(deviceType.getDeviceTypeName());
                    typeEntity.setTypeDescribe(deviceType.getDeviceTypeDescribe());
                    typeEntity.setCreateTime(deviceType.getDeviceTypeInsqlTime());
                    types.add(typeEntity);
                }
                styleEntity.setTypeList(types);
                styles.add(styleEntity);
            }
            classEntity.setStyleList(styles);
            classes.add(classEntity);
        }

        redisService.setValue(ALL_LIST_KEY, classes);

        return classes;
    }

    @Override
    public List<ClassEntity> searchClasses(String keyword) {
        // TODO: 如何搜索每一个级别
        throw new UnsupportedOperationException("接口未实现");
    }

    @Override
    public void createClass(ClassDTO classVO) {
        LocalDateTime now = LocalDateTime.now();
        redisService.remove(ALL_LIST_KEY);
        classDao.insertClass(DeviceClass.builder().
                deviceClassName(classVO.getClassName()).
                deviceClassDescribe(classVO.getClassDescribe()).
                deviceClassInsqlTime(now).
                deviceClassChangesqlTime(now)
                .build());
        redisService.delayedRemove(ALL_LIST_KEY);
    }

    @Override
    public void updateClass(ClassDTO classVO) {
        redisService.remove(ALL_LIST_KEY, CLASS_ID_KEY_PREFIX + classVO.getClassId());
        classDao.updateClass(DeviceClass.builder().
                deviceClassId(classVO.getClassId()).
                deviceClassName(classVO.getClassName()).
                deviceClassDescribe(classVO.getClassDescribe())
                .build());
        redisService.delayedRemove(ALL_LIST_KEY, CLASS_ID_KEY_PREFIX + classVO.getClassId());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteClass(Integer classId) {
        List<Integer> styleIds = classDao.queryStyleIdByClassId(classId);
        if (styleIds == null) {
            return;
        }
        // 删除子级
        for (Integer styleId : styleIds) {
            classDao.deleteTypeByStyleId(styleId);
            classDao.deleteStyle(styleId);
        }
        classDao.deleteClass(classId);
        // 注册事务提交后的缓存清理回调
        // 太麻烦，不用延迟双删了
        TransactionSynchronizationManager.registerSynchronization(
                new TransactionSynchronization() {
                    @Override
                    public void afterCommit() {
                        redisService.remove(ALL_LIST_KEY, CLASS_ID_KEY_PREFIX + classId);
                        for (Integer styleId : styleIds) {
                            redisService.remove(STYLE_ID_KEY_PREFIX + styleId);
                            List<Integer> typeIds = classDao.queryTypeIdByStyleId(styleId);
                            if (typeIds == null) {
                                continue;
                            }
                            for (Integer typeId : typeIds) {
                                redisService.remove(TYPE_ID_KEY_PREFIX + typeId);
                            }
                        }
                    }
                }
        );
    }

    @Override
    public void createStyle(ClassDTO styleVO, Integer parentId) {
        LocalDateTime now = LocalDateTime.now();
        redisService.remove(ALL_LIST_KEY);
        classDao.insertStyle(DeviceStyle.builder().
                deviceStyleName(styleVO.getClassName()).
                deviceStyleDescribe(styleVO.getClassDescribe()).
                deviceStyleClassId(parentId).
                deviceStyleInsqlTime(now).
                deviceStyleChangesqlTime(now)
                .build());
        redisService.delayedRemove(ALL_LIST_KEY);
    }

    @Override
    public void updateStyle(ClassDTO styleVO) {
        redisService.remove(ALL_LIST_KEY, STYLE_ID_KEY_PREFIX + styleVO.getClassId());
        classDao.updateStyle(DeviceStyle.builder().
                deviceStyleId(styleVO.getClassId()).
                deviceStyleName(styleVO.getClassName()).
                deviceStyleDescribe(styleVO.getClassDescribe())
                .build());
        redisService.delayedRemove(ALL_LIST_KEY, STYLE_ID_KEY_PREFIX + styleVO.getClassId());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteStyle(Integer styleId) {
        classDao.deleteTypeByStyleId(styleId);
        classDao.deleteStyle(styleId);
        TransactionSynchronizationManager.registerSynchronization(
                new TransactionSynchronization() {
                    @Override
                    public void afterCommit() {
                        redisService.remove(ALL_LIST_KEY, STYLE_ID_KEY_PREFIX + styleId);
                        List<Integer> typeIds = classDao.queryTypeIdByStyleId(styleId);
                        if (typeIds == null) {
                            return;
                        }
                        for (Integer typeId : typeIds) {
                            redisService.remove(TYPE_ID_KEY_PREFIX + typeId);
                        }
                    }
                }
        );
    }

    @Override
    public void createType(ClassDTO typeVO, Integer parentId) {
        LocalDateTime now = LocalDateTime.now();
        redisService.remove(ALL_LIST_KEY);
        classDao.insertType(DeviceType.builder().
                deviceTypeName(typeVO.getClassName()).
                deviceTypeDescribe(typeVO.getClassDescribe()).
                deviceTypeStyleId(parentId).
                deviceTypeInsqlTime(now).
                deviceTypeChangesqlTime(now)
                .build());
        redisService.delayedRemove(ALL_LIST_KEY);
    }

    @Override
    public void updateType(ClassDTO typeVO) {
        redisService.remove(ALL_LIST_KEY, TYPE_ID_KEY_PREFIX + typeVO.getClassId());
        classDao.updateType(DeviceType.builder().
                deviceTypeId(typeVO.getClassId()).
                deviceTypeName(typeVO.getClassName()).
                deviceTypeDescribe(typeVO.getClassDescribe())
                .build());
        redisService.delayedRemove(ALL_LIST_KEY, TYPE_ID_KEY_PREFIX + typeVO.getClassId());
    }


    @Override
    public void deleteType(Integer typeId) {
        redisService.remove(ALL_LIST_KEY, TYPE_ID_KEY_PREFIX + typeId);
        classDao.deleteType(typeId);
        redisService.delayedRemove(ALL_LIST_KEY, TYPE_ID_KEY_PREFIX + typeId);
    }

    // 只有在录入数据库的时候用到，不需要缓存
    @Override
    public Integer getClassIdByName(String className) {
        return classDao.queryClassIdByName(className);
    }

    @Override
    public Integer getStyleIdByName(String styleName, int parentId) {
        return classDao.queryStyleIdByName(styleName, parentId);
    }

    @Override
    public Integer getTypeIdByName(String typeName, int parentId) {
        return classDao.queryTypeIdByName(typeName, parentId);
    }
}
