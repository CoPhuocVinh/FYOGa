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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    // xu lu img
    @Override
    public void saveIMGAccount(MultipartFile file, Course course) throws IOException {
        // Kiểm tra xem tệp có tồn tại không
        if (!file.isEmpty()) {
            // Lưu tệp vào trường data của đối tượng Content
            course.setImg(file.getBytes());

            // Cập nhật các thông tin khác của đối tượng Content

            // Lưu đối tượng Content vào cơ sở dữ liệu
            courseRepository.save(course);
        }
    }

    @Override
    public <S extends Course> S save(S entity) {
        return courseRepository.save(entity);
    }

    @Override
    public byte[] getIMGById(int courseID) {
        // Truy vấn cơ sở dữ liệu để lấy đối tượng Content dựa trên contentId
        Course course = courseRepository.findById(courseID).orElse(null);

        if (course != null) {
            // Kiểm tra xem đối tượng Content có dữ liệu hình PNG không
            if (course.getImg() != null) {
                return course.getImg();
            } else {
                // Xử lý trường hợp không có dữ liệu hình PNG
                throw new RuntimeException("No PNG data found for course: " + courseID);
            }
        } else {
            // Xử lý trường hợp không tìm thấy đối tượng Content với contentId tương ứng
            throw new RuntimeException("User not found for course: " + courseID);
        }
    }
}
