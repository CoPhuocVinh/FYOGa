package org.jio.fyoga.service;/*  Welcome to Jio word
    @author: Jio
    Date: 6/22/2023
    Time: 11:55 PM
    
    ProjectName: demoSpring02
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.Course;

import java.util.List;
import java.util.Optional;

public interface ICourseService {
    List<Course> findAll();

    Course findCourseByCourse_name(String name);

    Optional<Course> findById(Integer integer);
}
