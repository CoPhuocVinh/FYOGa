package org.jio.fyoga.service;



import org.jio.fyoga.entity.Feedback;

import java.util.List;

public interface IFeedbackService {
    <S extends Feedback> S save(S entity);

    Feedback findById(Integer integer);

    List<Feedback> findAll();

    void deleteAll();
    List<Feedback> findByStatus(int status);

}
