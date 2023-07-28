package org.jio.fyoga.controllers.admin;

import org.jio.fyoga.entity.Class;
import org.jio.fyoga.entity.Course;
import org.jio.fyoga.entity.Package;
import org.jio.fyoga.model.CourseDTO;
import org.jio.fyoga.model.PackageDTO;
import org.jio.fyoga.service.ICourseService;
import org.jio.fyoga.service.IPackageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/FYoGa/Login/ADMIN/Package")
public class APackageController {

    @Autowired
    private IPackageService packageService;

    @Autowired
    private ICourseService courseService;

    @GetMapping("")
    public String getPackages(Model model) {
        List<Package> packageList = packageService.findAll();
        model.addAttribute("PACKAGELIST", packageList);

        return "admin/page_discount";
    }

    @GetMapping("/CreateOrEdit")
    public String showCreateOrEdit(@RequestParam int isEdit,
                                   @RequestParam(name = "PackageID", required = false, defaultValue = "-1") String R_PackageID,
                                   Model model) {
        int packageID = Integer.parseInt(R_PackageID);

        List<Course> courses = courseService.findAll();
        PackageDTO packageDTO = PackageDTO.builder().build();

        // Xử lý chức năng chỉnh sửa
        if (isEdit == 1 && packageID >= 0) {
            Optional<Package> packageEntityOptional = packageService.findById(packageID);
            if (packageEntityOptional.isPresent()) {
                Package packageEntity = packageEntityOptional.get();
                BeanUtils.copyProperties(packageEntity, packageDTO);
                packageDTO.setIsEdit(true);
                packageDTO.setCourseID(packageEntity.getCourse().getCourseID());
                packageDTO.setSlotOnMonth(packageEntity.getSlotOnMonth());
                packageDTO.setPrice(packageEntity.getPrice());
            }
        }

        // Xử lý chức năng tạo mới
        else if (isEdit == 0) {

            packageDTO.setIsEdit(false);
        }

        model.addAttribute("PACKAGEDTO", packageDTO);
        model.addAttribute("COURSES", courses);
        return "admin/add&editPackage";
    }

    @PostMapping("/CreateOrEdit")
    public String createOrEdit(@RequestParam boolean isEdit,
                               @RequestParam(name = "PackageID", required = false, defaultValue = "-1") int packageID,
                               @ModelAttribute("PACKAGEDTO") PackageDTO packageDTO,
                               RedirectAttributes ra) {
        Package packageEntity = new Package();

        if (!isEdit) {
            // Xử lý tạo mới
            Course course = courseService.findById(packageDTO.getCourseID()).orElse(null);
            if (course == null) {
                // Xử lý khi không tìm thấy khóa học với ID đã chọn
                ra.addFlashAttribute("MSG", "Invalid Course ID. Please choose a valid Course.");
                return "redirect:/FYoGa/Login/ADMIN/Package/CreateOrEdit?isEdit=0";
            }

            if (packageDTO.getSlotOnMonth() == 0) {
                ra.addFlashAttribute("MSG", "Number of sessions must be greater than 0.");
                return "redirect:/FYoGa/Login/ADMIN/Package/CreateOrEdit?isEdit=" + (isEdit ? "1&PackageID=" + packageID : "0");
            }

            if (packageDTO.getPrice() == 0) {
                ra.addFlashAttribute("MSG", "Price must be greater than 0.");
                return "redirect:/FYoGa/Login/ADMIN/Package/CreateOrEdit?isEdit=" + (isEdit ? "1&PackageID=" + packageID : "0");
            }

            // Kiểm tra xem gói học đã tồn tại hay chưa
            Package existingPackage = packageService.findPackageBySlotOnMonthAndCourse_CourseID(packageDTO.getSlotOnMonth(), packageDTO.getCourseID());
            if (existingPackage != null) {
                ra.addFlashAttribute("MSG", "Package with same Course and Slot on Month already exists.");
                return "redirect:/FYoGa/Login/ADMIN/Package/CreateOrEdit?isEdit=0";
            }

            BeanUtils.copyProperties(packageDTO, packageEntity);
            String packageName = packageDTO.getSlotOnMonth() + " buổi";
            packageEntity.setName(packageName);
            packageEntity.setCourse(course);
            packageEntity.setStatus(1);
        } else {
            // Xử lý chỉnh sửa
            Optional<Package> packageEntityOptional = packageService.findById(packageID);
            if (packageEntityOptional.isPresent()) {
                packageEntity = packageEntityOptional.get();
                Course course = courseService.findById(packageDTO.getCourseID()).orElse(null);
                if (course == null) {
                    // Xử lý khi không tìm thấy khóa học với ID đã chọn
                    ra.addFlashAttribute("MSG", "Invalid Course ID. Please choose a valid Course.");
                    return "redirect:/FYoGa/Login/ADMIN/Package/CreateOrEdit?isEdit=1&PackageID=" + packageID;
                }

                // Kiểm tra xem gói học đã tồn tại hay chưa
                Package existingPackage = packageService.findPackageBySlotOnMonthAndCourse_CourseID(packageDTO.getSlotOnMonth(), packageDTO.getCourseID());
                if (existingPackage != null && existingPackage.getPackageID() != packageEntity.getPackageID()) {
                    ra.addFlashAttribute("MSG", "Package with same Course and Slot on Month already exists. Please choose a different one.");
                    return "redirect:/FYoGa/Login/ADMIN/Package/CreateOrEdit?isEdit=1&PackageID=" + packageID;
                }

                BeanUtils.copyProperties(packageDTO, packageEntity);
                String packageName = packageDTO.getSlotOnMonth() + " buổi";
                packageEntity.setName(packageName);
                packageEntity.setCourse(course);
                packageEntity.setPrice(packageDTO.getPrice());
            }
        }

        packageService.save(packageEntity);
        ra.addFlashAttribute("MSG", "Save successfully!!!");
        return "redirect:/FYoGa/Login/ADMIN/Package";
    }
    @GetMapping("/remove")
    public String removeClass(@RequestParam int packageID) {
        packageService.deleteById(packageID);
        return "redirect:/FYoGa/Login/ADMIN/Package";
    }

}
