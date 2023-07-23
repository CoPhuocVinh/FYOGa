package org.jio.fyoga.service.impl;/*  Welcome to Jio word
    @author: Jio
    Date: 6/25/2023
    Time: 12:22 AM
    
    ProjectName: demoSpring02
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.Package;
import org.jio.fyoga.repository.PackageRepository;
import org.jio.fyoga.service.IPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackageServiceImpl implements IPackageService {

    @Autowired
    PackageRepository packageRepository;

    @Override
    public Optional<Package> findById(Integer integer) {
        return packageRepository.findById(integer);
    }


    public PackageServiceImpl(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    @Override
    public List<Package> findAllByCourse_CourseID(int CourseID) {
        return packageRepository.findAllByCourse_CourseID(CourseID);
    }

    @Override
    public List<Package> findAll() {
        return packageRepository.findAll();
    }

    @Override
    public void save(Package aPackage) {
        packageRepository.save(aPackage);
    }

    @Override
    public void deleteById(Integer packageId) {
        packageRepository.deleteById(packageId);
    }

    @Override
    public Package findPackageBySlotOnMonthAndCourse_CourseID(int slotOnMonth, int courseID) {
        return packageRepository.findPackageBySlotOnMonthAndCourse_CourseID(slotOnMonth, courseID);
    }
}
