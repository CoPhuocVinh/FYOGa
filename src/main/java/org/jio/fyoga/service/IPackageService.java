package org.jio.fyoga.service;/*  Welcome to Jio word
    @author: Jio
    Date: 6/25/2023
    Time: 12:21 AM
    
    ProjectName: demoSpring02
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.Package;

import java.util.List;
import java.util.Optional;

public interface IPackageService {
    Optional<Package> findById(Integer integer);

    List<Package> findAllByCourse_CourseID(int courseId);

    List<Package> findAll();

    void save(Package aPackage);

    void deleteById(Integer packageId);

    Package findPackageBySlotOnMonthAndCourse_CourseID(int slotOnMonth, int courseID);
}
