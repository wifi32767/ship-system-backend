package com.wifi32767.infra.adapter.repository;

import com.wifi32767.domain.system.adapter.repository.ClassRepository;
import com.wifi32767.domain.system.model.ClassVO;
import com.wifi32767.domain.system.model.StyleVO;
import com.wifi32767.domain.system.model.TypeVO;
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
    public List<ClassVO> getAllClasses() {
        List<ClassVO> classes = redisService.getValue(ALL_LIST_KEY);
        if (classes != null) {
            return classes;
        }

        List<DeviceClass> deviceClasses = classDao.getAllClass();
        classes = new ArrayList<>();

        // 如果有扩展层数的需求，可以用dfs责任链方便维护
        for (DeviceClass deviceClass : deviceClasses) {
            ClassVO classVO = new ClassVO();
            classVO.setClassId(deviceClass.getDeviceClassId());
            classVO.setClassName(deviceClass.getDeviceClassName());
            classVO.setClassDescribe(deviceClass.getDeviceClassDescribe());
            classVO.setCreateTime(deviceClass.getDeviceClassInsqlTime());

            List<DeviceStyle> deviceStyles = classDao.queryStyleByClassId(deviceClass.getDeviceClassId());
            List<StyleVO> styles = new ArrayList<>();
            for (DeviceStyle deviceStyle : deviceStyles) {
                StyleVO styleVO = new StyleVO();
                styleVO.setStyleId(deviceStyle.getDeviceStyleId());
                styleVO.setStyleName(deviceStyle.getDeviceStyleName());
                styleVO.setStyleDescribe(deviceStyle.getDeviceStyleDescribe());
                styleVO.setCreateTime(deviceStyle.getDeviceStyleInsqlTime());

                List<DeviceType> deviceTypes = classDao.queryTypeByStyleId(deviceStyle.getDeviceStyleId());
                List<TypeVO> types = new ArrayList<>();
                for (DeviceType deviceType : deviceTypes) {
                    TypeVO typeVO = new TypeVO();
                    typeVO.setTypeId(deviceType.getDeviceTypeId());
                    typeVO.setTypeName(deviceType.getDeviceTypeName());
                    typeVO.setTypeDescribe(deviceType.getDeviceTypeDescribe());
                    typeVO.setCreateTime(deviceType.getDeviceTypeInsqlTime());
                    types.add(typeVO);
                }
                styleVO.setTypeList(types);
                styles.add(styleVO);
            }
            classVO.setStyleList(styles);
            classes.add(classVO);
        }

        redisService.setValue(ALL_LIST_KEY, classes);

        return classes;
    }

    @Override
    public List<ClassVO> searchClasses(String keyword) {
        // TODO: 如何搜索每一个级别
        throw new UnsupportedOperationException("接口未实现");
    }

    @Override
    public void createClass(TypeVO classVO) {
        LocalDateTime now = LocalDateTime.now();
        redisService.remove(ALL_LIST_KEY);
        classDao.insertClass(DeviceClass.builder().
                deviceClassName(classVO.getTypeName()).
                deviceClassDescribe(classVO.getTypeDescribe()).
                deviceClassInsqlTime(now).
                deviceClassChangesqlTime(now)
                .build());
        redisService.delayedRemove(ALL_LIST_KEY);
    }

    @Override
    public void updateClass(TypeVO classVO) {
        redisService.remove(ALL_LIST_KEY, CLASS_ID_KEY_PREFIX + classVO.getTypeId());
        classDao.updateClass(DeviceClass.builder().
                deviceClassId(classVO.getTypeId()).
                deviceClassName(classVO.getTypeName()).
                deviceClassDescribe(classVO.getTypeDescribe())
                .build());
        redisService.delayedRemove(ALL_LIST_KEY, CLASS_ID_KEY_PREFIX + classVO.getTypeId());
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
    public void createStyle(TypeVO styleVO, Integer parentId) {
        LocalDateTime now = LocalDateTime.now();
        redisService.remove(ALL_LIST_KEY);
        classDao.insertStyle(DeviceStyle.builder().
                deviceStyleName(styleVO.getTypeName()).
                deviceStyleDescribe(styleVO.getTypeDescribe()).
                deviceStyleClassId(parentId).
                deviceStyleInsqlTime(now).
                deviceStyleChangesqlTime(now)
                .build());
        redisService.delayedRemove(ALL_LIST_KEY);
    }

    @Override
    public void updateStyle(TypeVO styleVO) {
        redisService.remove(ALL_LIST_KEY, STYLE_ID_KEY_PREFIX + styleVO.getTypeId());
        classDao.updateStyle(DeviceStyle.builder().
                deviceStyleId(styleVO.getTypeId()).
                deviceStyleName(styleVO.getTypeName()).
                deviceStyleDescribe(styleVO.getTypeDescribe())
                .build());
        redisService.delayedRemove(ALL_LIST_KEY, STYLE_ID_KEY_PREFIX + styleVO.getTypeId());
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
    public void createType(TypeVO typeVO, Integer parentId) {
        LocalDateTime now = LocalDateTime.now();
        redisService.remove(ALL_LIST_KEY);
        classDao.insertType(DeviceType.builder().
                deviceTypeName(typeVO.getTypeName()).
                deviceTypeDescribe(typeVO.getTypeDescribe()).
                deviceTypeStyleId(parentId).
                deviceTypeInsqlTime(now).
                deviceTypeChangesqlTime(now)
                .build());
        redisService.delayedRemove(ALL_LIST_KEY);
    }

    @Override
    public void updateType(TypeVO typeVO) {
        redisService.remove(ALL_LIST_KEY, TYPE_ID_KEY_PREFIX + typeVO.getTypeId());
        classDao.updateType(DeviceType.builder().
                deviceTypeId(typeVO.getTypeId()).
                deviceTypeName(typeVO.getTypeName()).
                deviceTypeDescribe(typeVO.getTypeDescribe())
                .build());
        redisService.delayedRemove(ALL_LIST_KEY, TYPE_ID_KEY_PREFIX + typeVO.getTypeId());
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
