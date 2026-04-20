package com.wifi32767.infra.dao;

import com.wifi32767.infra.dao.po.DeviceClass;
import com.wifi32767.infra.dao.po.DeviceStyle;
import com.wifi32767.infra.dao.po.DeviceType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassDao {
    String queryClassNameById(Integer classId);

    List<DeviceClass> getAllClass();

    List<DeviceStyle> queryStyleByClassId(Integer classId);

    List<DeviceType> queryTypeByStyleId(Integer styleId);

    void insertClass(DeviceClass deviceClass);

    void insertStyle(DeviceStyle deviceStyle);

    void insertType(DeviceType deviceType);

    void updateClass(DeviceClass deviceClass);

    void updateStyle(DeviceStyle deviceStyle);

    void updateType(DeviceType deviceType);

    void deleteClass(Integer classId);

    void deleteStyle(Integer styleId);

    void deleteType(Integer typeId);

    List<Integer> queryStyleIdByClassId(Integer classId);

    List<Integer> queryTypeIdByStyleId(Integer styleId);

    void deleteStyleByClassId(Integer classId);

    void deleteTypeByStyleId(Integer styleId);
}
