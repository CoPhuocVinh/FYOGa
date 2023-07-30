package org.jio.fyoga.repository;

import org.jio.fyoga.entity.Class;
import org.jio.fyoga.entity.Feedback;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    List<Feedback> findByStatus(int status);
}
