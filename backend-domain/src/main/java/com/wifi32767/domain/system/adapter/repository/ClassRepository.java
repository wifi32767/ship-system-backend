package com.wifi32767.domain.system.adapter.repository;

import com.wifi32767.domain.system.model.ClassVO;
import com.wifi32767.domain.system.model.TypeVO;

import java.util.List;

public interface ClassRepository {
    String getClassNameById(int classId);

    String getStyleNameById(int styleId);

    String getTypeNameById(int typeId);

    List<ClassVO> getAllClasses();

    List<ClassVO> searchClasses(String keyword);

    void createClass(TypeVO classVO);

    void updateClass(TypeVO classVO);

    void deleteClass(Integer classId);

    void createStyle(TypeVO styleVO, Integer parentId);

    void updateStyle(TypeVO styleVO);

    void deleteStyle(Integer styleId);

    void createType(TypeVO typeVO, Integer parentId);

    void updateType(TypeVO typeVO);

    void deleteType(Integer typeId);

    Integer getClassIdByName(String className);

    Integer getStyleIdByName(String styleName, int parentId);

    Integer getTypeIdByName(String typeName, int parentId);
}
