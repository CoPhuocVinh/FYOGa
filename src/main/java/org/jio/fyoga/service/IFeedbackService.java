package org.jio.fyoga.service;



import org.jio.fyoga.entity.Feedback;
import org.jio.fyoga.model.FeedbackDTO;

import java.util.List;

public interface IFeedbackService {
    <S extends Feedback> S save(S entity);


    Feedback findById(int feedbackID);

    Feedback save1(Feedback feedback);

    List<Feedback> findAll();

    void deleteAll();





    List<Feedback> findByStatus(int status);

}
