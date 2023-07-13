package org.jio.fyoga.service;

import org.jio.fyoga.entity.Class;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface IClassService {
    <S extends Class> S save(S entity);

    Class findById(Integer integer);

    List<Class> findAll();

    void deleteAll();


    List<Class> findByStatus(int status);
}