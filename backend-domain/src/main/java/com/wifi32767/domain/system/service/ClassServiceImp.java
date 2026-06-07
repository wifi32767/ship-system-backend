package com.wifi32767.domain.system.service;

import com.wifi32767.domain.system.adapter.repository.ClassRepository;
import com.wifi32767.domain.system.model.ClassDTO;
import com.wifi32767.domain.system.model.ClassEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClassServiceImp implements ClassService {

    @Resource
    private ClassRepository classRepository;

    @Override
    public List<ClassEntity> getClasses() {
        return classRepository.getAllClasses();
    }

    @Override
    public List<ClassEntity> searchClasses(String keyword) {
        return classRepository.searchClasses(keyword);
    }

    @Override
    public void createClass(ClassDTO classVO) {
        if (classVO == null) {
            return;
        }
        classRepository.createClass(classVO);
    }

    @Override
    public void updateClass(ClassDTO classVO) {
        if (classVO == null || classVO.getClassId() == null) {
            return;
        }
        classRepository.updateClass(classVO);
    }

    @Override
    public void deleteClass(Integer classId) {
        if (classId == null) {
            return;
        }
        classRepository.deleteClass(classId);
    }

    @Override
    public void createStyle(ClassDTO styleVO, Integer parentId) {
        if (styleVO == null || parentId == null) {
            return;
        }
        classRepository.createStyle(styleVO, parentId);
    }

    @Override
    public void updateStyle(ClassDTO styleVO) {
        if (styleVO == null || styleVO.getClassId() == null) {
            return;
        }
        classRepository.updateStyle(styleVO);
    }

    @Override
    public void deleteStyle(Integer styleId) {
        if (styleId == null) {
            return;
        }
        classRepository.deleteStyle(styleId);
    }

    @Override
    public void createType(ClassDTO typeVO, Integer parentId) {
        if (typeVO == null || parentId == null) {
            return;
        }
        classRepository.createType(typeVO, parentId);
    }

    @Override
    public void updateType(ClassDTO typeVO) {
        if (typeVO == null || typeVO.getClassId() == null) {
            return;
        }
        classRepository.updateType(typeVO);
    }

    @Override
    public void deleteType(Integer typeId) {
        if (typeId == null) {
            return;
        }
        classRepository.deleteType(typeId);
    }
}
