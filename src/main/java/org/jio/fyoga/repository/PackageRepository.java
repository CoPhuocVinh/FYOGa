package org.jio.fyoga.repository;/*  Welcome to Jio word
    @author: Jio
    Date: 6/25/2023
    Time: 12:19 AM
    
    ProjectName: demoSpring02
    Jio: I wish you always happy with coding <3
*/
import org.jio.fyoga.entity.Package;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Package, Integer> {
    List<Package> findAllByDefaultIDAndAndCourse_CourseID(int DefaultId, int CourseId);

}
