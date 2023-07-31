package org.jio.fyoga.repository;/*  Welcome to Jio word
    @author: Jio
    Date: 6/22/2023
    Time: 11:53 PM
    
    ProjectName: demoSpring02
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {


    Course findCourseByName(final String course_name);

    List<Course> findByStatus(int status);

    List<Course> findAllByStatus(int status);
}
