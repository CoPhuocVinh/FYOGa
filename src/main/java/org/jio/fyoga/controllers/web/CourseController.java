package org.jio.fyoga.controllers.web;/*  Welcome to Jio word
    @author: Jio
    Date: 6/28/2023
    Time: 1:17 PM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import jakarta.servlet.http.HttpSession;
import org.jio.fyoga.entity.Course;
import org.jio.fyoga.entity.Package;
import org.jio.fyoga.model.PackageDTO;
import org.jio.fyoga.service.ICourseService;
import org.jio.fyoga.service.IPackageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/FYoGa/Course")
@Controller
public class CourseController {

    @Autowired
    ICourseService courseService;
    @Autowired
    IPackageService packageService;

    // xu ly course trong
    @GetMapping("")
    public String showCourse(@RequestParam String name, HttpSession session, Model model) {
        //ArrayList<Course> COURSES = (ArrayList<Course>) session.getAttribute("COURSES");
        Course course = courseService.findCourseByCourse_name(name);

        String url = "redirect:/";
        if( course != null){
            model.addAttribute("COURSE",course);

            List<Package> packages = packageService.findAllByDefaultIDaAndAndCourse_CourseID(0, course.getCourseID());
            List<PackageDTO> packageDTOs = tranferDTO2Entity(packages);

            if (packages != null && packages.size() != 0){
                model.addAttribute("PACKAGES", packageDTOs);
            }
            else {
                model.addAttribute("MSG", "Chưa có gói nào cho khóa này. Xin vui lòng quay lại sau");
            }
            url = "web/course";
        }
        return url;
    }

    @GetMapping("/Package")
    public String showPackage(@RequestParam int courseID, @RequestParam int packageID, Model model){
        List<Package> packageEntitys = new ArrayList<>();
        Optional<Package> pkg = packageService.findById(packageID);


        model.addAttribute("PACKE",pkg );

        if (pkg.isPresent()){
            Package pack = pkg.orElseThrow();
            packageEntitys.add(pack);
            
        }
        for (Package apack:packageService.findAllByDefaultIDaAndAndCourse_CourseID(packageID, courseID)) {
            packageEntitys.add(apack);
            
        }

        List<PackageDTO> packageDTOs = tranferDTO2Entity(packageEntitys);

        Optional<Course> course =  courseService.findById(courseID);
        if ((course.isPresent())) {
            model.addAttribute("COURSE", course);
        }



        model.addAttribute("PACKAGES", packageDTOs);


        return "web/pakageCourse";
    }


    public List<PackageDTO> tranferDTO2Entity(List<Package> packagesEntitys){
        List<PackageDTO> packageDTOs = new ArrayList<>();

        for (Package packageEntiry : packagesEntitys){
            PackageDTO packageDTO = new PackageDTO();
            BeanUtils.copyProperties(packageEntiry, packageDTO);
            float priceDiscount = packageDTO.getPrice() * (100 - packageDTO.getPercentDiscount())/100 ;
            packageDTO.setPriceDiscount(priceDiscount);
            packageDTOs.add(packageDTO);

        }

        return packageDTOs;
    }



}
