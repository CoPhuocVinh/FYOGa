package org.jio.fyoga.service.impl;/*  Welcome to Jio word
    @author: Jio
    Date: 6/22/2023
    Time: 11:55 PM
    
    ProjectName: demoSpring02
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.Course;
import org.jio.fyoga.repository.CourseRepository;
import org.jio.fyoga.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements ICourseService {
    @Autowired
    CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findCourseByCourse_name(String name) {
        return courseRepository.findCourseByName(name);
    }

    @Override
    public Optional<Course> findById(Integer integer) {
        return courseRepository.findById(integer);
    }
}
