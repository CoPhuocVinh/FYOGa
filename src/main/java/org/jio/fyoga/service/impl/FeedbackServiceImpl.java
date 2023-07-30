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

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository){this.feedbackRepository = feedbackRepository;}
    @Override
    public <S extends Feedback> S save(S entity) {
        return feedbackRepository.save(entity);
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
        return this.feedbackRepository.findByStatus(status);
    }
}
