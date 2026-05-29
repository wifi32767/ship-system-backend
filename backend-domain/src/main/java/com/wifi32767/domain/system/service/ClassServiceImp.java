package com.wifi32767.domain.system.service;

import com.wifi32767.domain.system.adapter.repository.ClassRepository;
import com.wifi32767.domain.system.model.ClassEntity;
import com.wifi32767.domain.system.model.TypeEntity;
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
    public void createClass(TypeEntity classVO) {
        classRepository.createClass(classVO);
    }

    @Override
    public void updateClass(TypeEntity classVO) {
        classRepository.updateClass(classVO);
    }

    @Override
    public void deleteClass(Integer classId) {
        classRepository.deleteClass(classId);
    }

    @Override
    public void createStyle(TypeEntity styleVO, Integer parentId) {
        classRepository.createStyle(styleVO, parentId);
    }

    @Override
    public void updateStyle(TypeEntity styleVO) {
        classRepository.updateStyle(styleVO);
    }

    @Override
    public void deleteStyle(Integer styleId) {
        classRepository.deleteStyle(styleId);
    }

    @Override
    public void createType(TypeEntity typeEntity, Integer parentId) {
        classRepository.createType(typeEntity, parentId);
    }

    @Override
    public void updateType(TypeEntity typeEntity) {
        classRepository.updateType(typeEntity);
    }

    @Override
    public void deleteType(Integer typeId) {
        classRepository.deleteType(typeId);
    }
}
