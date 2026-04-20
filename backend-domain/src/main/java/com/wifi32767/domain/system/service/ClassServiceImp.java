package com.wifi32767.domain.system.service;

import com.wifi32767.domain.system.adapter.repository.ClassRepository;
import com.wifi32767.domain.system.model.ClassVO;
import com.wifi32767.domain.system.model.TypeVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClassServiceImp implements ClassService {

    @Resource
    private ClassRepository classRepository;

    @Override
    public List<ClassVO> getClasses() {
        return classRepository.getAllClasses();
    }

    @Override
    public List<ClassVO> searchClasses(String keyword) {
        return classRepository.searchClasses(keyword);
    }

    @Override
    public void createClass(TypeVO classVO) {
        classRepository.createClass(classVO);
    }

    @Override
    public void updateClass(TypeVO classVO) {
        classRepository.updateClass(classVO);
    }

    @Override
    public void deleteClass(Integer classId) {
        classRepository.deleteClass(classId);
    }

    @Override
    public void createStyle(TypeVO styleVO, Integer parentId) {
        classRepository.createStyle(styleVO, parentId);
    }

    @Override
    public void updateStyle(TypeVO styleVO) {
        classRepository.updateStyle(styleVO);
    }

    @Override
    public void deleteStyle(Integer styleId) {
        classRepository.deleteStyle(styleId);
    }

    @Override
    public void createType(TypeVO typeVO, Integer parentId) {
        classRepository.createType(typeVO, parentId);
    }

    @Override
    public void updateType(TypeVO typeVO) {
        classRepository.updateType(typeVO);
    }

    @Override
    public void deleteType(Integer typeId) {
        classRepository.deleteType(typeId);
    }
}
