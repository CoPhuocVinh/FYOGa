package org.jio.fyoga.service.impl;

import org.jio.fyoga.entity.Class;
import org.jio.fyoga.repository.ClassRepository;
import org.jio.fyoga.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements IClassService {
    @Autowired
    ClassRepository classRepository;

    public ClassServiceImpl(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @Override
    public <S extends Class> S save(S entity) {
        return classRepository.save(entity);
    }

    @Override
    public Class findById(Integer integer) {

        return classRepository.findById(integer).orElseThrow();
    }

    @Override
    public List<Class> findAll() {
        return classRepository.findAll();
    }

    @Override
    public void deleteAll() {
        classRepository.deleteAll();
    }

    @Override
    public List<Class> findByStatus(int status) {

        return classRepository.findByStatus(status);
    }

    @Override
    public Class findFirstByOrderByCreateDayDesc() {
        return classRepository.findFirstByOrderByCreateDayDesc();
    }

    @Override
    public Class findFirstByOrderByClassIDDesc() {
        return classRepository.findFirstByOrderByClassIDDesc();
    }

}