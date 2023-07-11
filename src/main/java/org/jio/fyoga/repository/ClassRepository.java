package org.jio.fyoga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.jio.fyoga.entity.Class;

@Repository
public interface ClassRepository extends JpaRepository<Class, Integer> {

}