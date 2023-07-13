package org.jio.fyoga.repository;

import org.jio.fyoga.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.jio.fyoga.entity.Class;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class, Integer> {
    List<Class> findByStatus(int status);

    Class findFirstByOrderByCreateDayDesc();

    Class findFirstByOrderByClassIDDesc();


}