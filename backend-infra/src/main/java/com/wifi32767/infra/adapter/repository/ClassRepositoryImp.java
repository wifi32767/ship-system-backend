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
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<ClassVO> getAllClasses() {
        List<DeviceClass> deviceClasses = classDao.getAllClass();
        List<ClassVO> classes = new ArrayList<>();

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
            }
            classVO.setStyleList(styles);
            classes.add(classVO);
        }
        return classes;
    }

    @Override
    public List<ClassVO> searchClasses(String keyword) {
        // TODO: 如何搜索每一个级别
        return null;
    }

    @Override
    public void createClass(TypeVO classVO) {
        LocalDateTime now = LocalDateTime.now();
        classDao.insertClass(DeviceClass.builder().
                deviceClassName(classVO.getTypeName()).
                deviceClassDescribe(classVO.getTypeDescribe()).
                deviceClassInsqlTime(now).
                deviceClassChangesqlTime(now)
                .build());
    }

    @Override
    public void updateClass(TypeVO classVO) {
        classDao.updateClass(DeviceClass.builder().
                deviceClassId(classVO.getTypeId()).
                deviceClassName(classVO.getTypeName()).
                deviceClassDescribe(classVO.getTypeDescribe())
                .build());
    }

    @Override
    public void deleteClass(Integer classId) {
        List<Integer> styleIds = classDao.queryStyleIdByClassId(classId);
        // 删除子级
        // TODO: 事务
        if (styleIds != null) {
            for (Integer styleId : styleIds) {
                deleteStyle(styleId);
            }
        }
        classDao.deleteClass(classId);
    }

    @Override
    public void createStyle(TypeVO styleVO, Integer parentId) {
        LocalDateTime now = LocalDateTime.now();
        classDao.insertStyle(DeviceStyle.builder().
                deviceStyleName(styleVO.getTypeName()).
                deviceStyleDescribe(styleVO.getTypeDescribe()).
                deviceStyleClassId(parentId).
                deviceStyleInsqlTime(now).
                deviceStyleChangesqlTime(now)
                .build());
    }

    @Override
    public void updateStyle(TypeVO styleVO) {
        classDao.updateStyle(DeviceStyle.builder().
                deviceStyleId(styleVO.getTypeId()).
                deviceStyleName(styleVO.getTypeName()).
                deviceStyleDescribe(styleVO.getTypeDescribe())
                .build());
    }

    @Override
    public void deleteStyle(Integer styleId) {
        // TODO: 写在一个事务
        classDao.deleteTypeByStyleId(styleId);
        classDao.deleteStyle(styleId);
    }

    @Override
    public void createType(TypeVO typeVO, Integer parentId) {
        LocalDateTime now = LocalDateTime.now();
        classDao.insertType(DeviceType.builder().
                deviceTypeName(typeVO.getTypeName()).
                deviceTypeDescribe(typeVO.getTypeDescribe()).
                deviceTypeStyleId(parentId).
                deviceTypeInsqlTime(now).
                deviceTypeChangesqlTime(now)
                .build());
    }

    @Override
    public void updateType(TypeVO typeVO) {
        classDao.updateType(DeviceType.builder().
                deviceTypeId(typeVO.getTypeId()).
                deviceTypeName(typeVO.getTypeName()).
                deviceTypeDescribe(typeVO.getTypeDescribe())
                .build());
    }

    @Override
    public void deleteType(Integer typeId) {
        classDao.deleteType(typeId);
    }
}
