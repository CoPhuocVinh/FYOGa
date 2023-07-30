package org.jio.fyoga.service.impl;

import org.jio.fyoga.entity.Feedback;
import org.jio.fyoga.repository.FeedbackRepository;
import org.jio.fyoga.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements IFeedbackService {

    @Autowired
    FeedbackRepository feedbackRepository;


    @Override
    public Feedback save(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback findById(Integer integer) {
        return feedbackRepository.findById(integer).orElseThrow();
    }

    @Override
    public List<Feedback> findAll() {
        return feedbackRepository.findAll();
    }

    @Override
    public void deleteAll() {
        feedbackRepository.deleteAll();
    }

    @Override
    public List<Feedback> findByStatus(int status) {
        // Make sure the feedbackRepository is not null
        if (feedbackRepository == null) {
            throw new RuntimeException("Feedback repository is not initialized.");
        }

        // Perform the actual data retrieval
        List<Feedback> feedbackList = feedbackRepository.findByStatus(status);

        // Make sure the returned list is not null (optional, depending on your requirements)
        if (feedbackList == null) {
            throw new RuntimeException("Feedback list is null.");
        }

        return feedbackList;
    }
}
