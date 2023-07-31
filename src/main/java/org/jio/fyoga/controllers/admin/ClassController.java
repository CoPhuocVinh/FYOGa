package org.jio.fyoga.controllers.admin;

import jakarta.servlet.http.HttpSession;
import org.jio.fyoga.entity.Account;
import org.jio.fyoga.entity.Class;
import org.jio.fyoga.entity.Course;
import org.jio.fyoga.model.AccountDTO;
import org.jio.fyoga.model.ClassDTO;
import org.jio.fyoga.service.IAccountService;
import org.jio.fyoga.service.IClassService;
import org.jio.fyoga.service.ICourseService;
import org.jio.fyoga.util.MyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.util.List;

@RequestMapping("/FYoGa/Login/ADMIN/class")
@Controller
public class ClassController {
    @Autowired
    private IClassService classService;

    @Autowired
    private ICourseService courseService;
    @Autowired
    private IAccountService accountService;



// /FYoGa/Login/ADMIN/class
    @GetMapping("")
    public String getClass(HttpSession session,Model model) {
        if (MyUtil.checkAuthen(session)) {
            List<Class> classListON = classService.findByStatus(1);
            model.addAttribute("CLASS_ON", classListON);

            List<Class> classListOff = classService.findByStatus(0);
            model.addAttribute("CLASS_OFF", classListOff);

            return "admin/page_list_class";
        }
        return "web/login";

    }
// /FYoGa/Login/ADMIN/class/CreateOrUpdate
    @GetMapping("/CreateOrUpdate")
    public String ShowCreateOrUpdate(@RequestParam int isEdit, HttpSession session,
                                     @RequestParam(name = "ClassID",required = false, defaultValue = "-1") String R_ClassID,
                                     Model model){

        if (MyUtil.checkAuthen(session)) {
            int ClassID = Integer.parseInt(R_ClassID);

            List<Course> courses = courseService.findAll();
            List<Account> accounts = accountService.findAccountByRole(2);
            ClassDTO classDTO = ClassDTO.builder().build();
            // xu ly edit
            if(isEdit == 1 && ClassID >= 0){
                Class classEntity = classService.findById(ClassID);
                BeanUtils.copyProperties(classEntity, classDTO);
                classDTO.setIsEdit(true);
                classDTO.setTeacherID(classEntity.getTeacher().getAccountID());
                classDTO.setCourseID(classEntity.getCourse().getCourseID());
            }

            //xu ly CREATE
            if(isEdit == 0){
                classDTO.setIsEdit(false);
            }
            model.addAttribute("CLASSDTO", classDTO);
            model.addAttribute("COURSES", courses);
            model.addAttribute("ACCOUNTS", accounts);

            return "admin/createnewclass";
        }
        return "web/login";

    }

    @PostMapping("/CreateOrUpdate")
    public String CreateOrUpdate(HttpSession session, @ModelAttribute("CLASSDTO")ClassDTO classDTO
            , RedirectAttributes ra){
        if (MyUtil.checkAuthen(session)) {
            Class aClassEntity = new Class();
            if (classDTO.getIsEdit()){
                aClassEntity = classService.findById(classDTO.getClassID());
                aClassEntity.setCourse(courseService.findById(classDTO.getCourseID()).orElseThrow());
                aClassEntity.setTeacher(accountService.findById(classDTO.getTeacherID()));
                aClassEntity.setQuantityClass(classDTO.getQuantityClass());
            }else {
                //        xử lý tạo mới
                // copy tu model sang entity
                BeanUtils.copyProperties(classDTO, aClassEntity);
                Date date = new Date(System.currentTimeMillis());
                aClassEntity.setCreateDay(date);
                aClassEntity.setTeacher(accountService.findById(classDTO.getTeacherID()));
                aClassEntity.setCourse(courseService.findById(classDTO.getCourseID()).orElseThrow());
                aClassEntity.setStatus(1);
                aClassEntity.setClassName("yoga");
                classService.save(aClassEntity);
                String name = "yoga " + classService.findFirstByOrderByClassIDDesc().getClassID();
                aClassEntity.setClassName(name);
                Account account = (Account) session.getAttribute("USER");
                aClassEntity.setStaff(account.getAccountID());

            }

            classService.save(aClassEntity);

            ra.addFlashAttribute("MSG","Save successfully!!!");

            return "redirect:/FYoGa/Login/ADMIN/class";
        }
        return "web/login";
    }

    @GetMapping("/isremove")
    public String showdelete(RedirectAttributes ra, @RequestParam int classID){
        ra.addAttribute("DELETE",classID);

        return "redirect:/FYoGa/Login/ADMIN/class";
    }
//@PathVariable int classID
//    @GetMapping("/remove/{classID}")
    @GetMapping("/remove")
    public String removeClass(@RequestParam int classID) {
        //classService.deleteById(classID);
        Class aClass = classService.findById(classID);
        aClass.setStatus(0);
        classService.save(aClass);
        return "redirect:/FYoGa/Login/ADMIN/class";
    }

    @GetMapping("/reStatus")
    public String hoantac(@RequestParam int classID) {
        //classService.deleteById(classID);
        Class aClass = classService.findById(classID);
        aClass.setStatus(1);
        classService.save(aClass);
        return "redirect:/FYoGa/Login/ADMIN/class";
    }



}