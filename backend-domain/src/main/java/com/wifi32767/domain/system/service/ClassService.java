package com.wifi32767.domain.system.service;

import com.wifi32767.domain.system.model.ClassEntity;
import com.wifi32767.domain.system.model.TypeEntity;

import java.util.List;

public interface ClassService {
    List<ClassEntity> getClasses();

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
}
