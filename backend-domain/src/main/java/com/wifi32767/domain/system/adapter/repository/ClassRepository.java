package com.wifi32767.domain.system.adapter.repository;

import com.wifi32767.domain.system.model.ClassDTO;
import com.wifi32767.domain.system.model.ClassEntity;

import java.util.List;

public interface ClassRepository {
    String getClassNameById(int classId);

    String getStyleNameById(int styleId);

    String getTypeNameById(int typeId);

    List<ClassEntity> getAllClasses();

    List<ClassEntity> searchClasses(String keyword);

    void createClass(ClassDTO classVO);

    void updateClass(ClassDTO classVO);

    void deleteClass(Integer classId);

    void createStyle(ClassDTO styleVO, Integer parentId);

    void updateStyle(ClassDTO styleVO);

    void deleteStyle(Integer styleId);

    void createType(ClassDTO typeEntity, Integer parentId);

    void updateType(ClassDTO typeEntity);

    void deleteType(Integer typeId);

    Integer getClassIdByName(String className);

    Integer getStyleIdByName(String styleName, int parentId);

    Integer getTypeIdByName(String typeName, int parentId);
}
