package com.wifi32767.domain.system.adapter.repository;

import com.wifi32767.domain.system.model.ClassEntity;
import com.wifi32767.domain.system.model.TypeEntity;

import java.util.List;

public interface ClassRepository {
    String getClassNameById(int classId);

    String getStyleNameById(int styleId);

    String getTypeNameById(int typeId);

    List<ClassEntity> getAllClasses();

    List<ClassEntity> searchClasses(String keyword);

    void createClass(TypeEntity classVO);

    void updateClass(TypeEntity classVO);

    void deleteClass(Integer classId);

    void createStyle(TypeEntity styleVO, Integer parentId);

    void updateStyle(TypeEntity styleVO);

    void deleteStyle(Integer styleId);

    void createType(TypeEntity typeEntity, Integer parentId);

    void updateType(TypeEntity typeEntity);

    void deleteType(Integer typeId);

    Integer getClassIdByName(String className);

    Integer getStyleIdByName(String styleName, int parentId);

    Integer getTypeIdByName(String typeName, int parentId);
}
