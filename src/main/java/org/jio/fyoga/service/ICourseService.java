package org.jio.fyoga.service;/*  Welcome to Jio word
    @author: Jio
    Date: 6/22/2023
    Time: 11:55 PM
    
    ProjectName: demoSpring02
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.Course;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ICourseService {

    void deleteById(Integer integer);

    List<Course> findAll();

    Course findCourseByCourse_name(String name);

    Course findCourseByName(String course_name);

    Optional<Course> findById(Integer integer);

    // xu lu img
    void saveIMGAccount(MultipartFile file, Course course) throws IOException;

    <S extends Course> S save(S entity);

    byte[] getIMGById(int courseID);


    List<Course> findByStatus(int status);
}
