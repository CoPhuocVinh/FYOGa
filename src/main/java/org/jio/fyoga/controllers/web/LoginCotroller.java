package org.jio.fyoga.controllers.web;/*  Welcome to Jio word
    @author: Jio
    Date: 6/11/2023
    Time: 11:21 PM
    
    ProjectName: demoSpring
    Jio: I wish you always happy with coding <3
*/

import jakarta.servlet.http.HttpSession;
import org.jio.fyoga.entity.Account;
import org.jio.fyoga.entity.Course;
import org.jio.fyoga.repository.CourseRepository;
import org.jio.fyoga.repository.RegisterRepository;
import org.jio.fyoga.service.IAccountService;
import org.jio.fyoga.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@RequestMapping("/FYoGa/Login")
@Controller
public class LoginCotroller {
    @Autowired
    IAccountService accountService;

    @RequestMapping("")
    public String showLoginFYoGa(HttpSession session) {

        return MyUtil.Authen(session);
    }

    @PostMapping("/CheckLoginFYoGa")
    public String showLogin(@RequestParam Map<String, Object> params, ModelMap model, HttpSession session) {
        String email = (String) params.get("email");
        String password = (String) params.get("password");
        Account account = accountService.checkLogin(email, password);

        if (account != null) {
            System.out.println("login thành công");
            session.setAttribute("USER", account);

            //xử lý checkouting

            try {
                int discountID = (int) session.getAttribute("CHECKOUTING");
                session.removeAttribute("CHECKOUTING");
                return "redirect:/FYoGa/Course/PackageCheckOut?discountID=" + discountID;

            }catch (Exception ex){

            }

            //model.addAttribute("USER", account);
            if (account.getRole().getRoleID() == 1) {
                return "redirect:/FYoGa/Login/User";
                //return "web/user";
            } else if (account.getRole().getRoleID() == 2) {
                return "redirect:/FYoGa/Login/HLV";
                //return "web/hlv";
            } else if (account.getRole().getRoleID() == 3) {
                return "redirect:/FYoGa/Login/Staff";
                //return "web/index";
            } else if (account.getRole().getRoleID() == 4) {
                return "redirect:/FYoGa/Login/ADMIN";
                //return "admin/admin";
            }
            return "web/index";
        } else {
            System.out.println("login thất bại");
            model.addAttribute("ERROR", "Email hoặc mật khẩu không đúng !!!");
            model.addAttribute("EMAIL", email);
            return "web/login";
        }
    }

    //
    @Autowired
    RegisterRepository registerRepository;

    //@RequestMapping(value = { "/FYoGa", "/FYoGa/"})

    @GetMapping(value = {"/User","/User/"})
    public String showUser(HttpSession session, Model model) {
        Account account = (Account) session.getAttribute("USER");

        if (account != null && account.getRole().getRoleID() == 1){
            List<Course> courses = registerRepository.findCoursesByUserRegister(account.getAccountID());
            for (Course course : courses){
                System.out.println(course.getCourseID());
            }
            model.addAttribute("COURSES",courses);
            return "web/user";
        }
        else
            return "redirect:/";
    }

    @GetMapping("/HLV")
    public String showHLV(HttpSession session) {
        Account account = (Account) session.getAttribute("USER");
        if (account != null && account.getRole().getRoleID() == 2)
            return "web/hlv";
        else
            return "redirect:/";
    }

    @GetMapping("/ADMIN")
    public String showADMIN(HttpSession session) {
        Account account = (Account) session.getAttribute("USER");
        if (account != null && account.getRole().getRoleID() == 4)
            return "admin/admin";
        else
            return "redirect:/";
    }

    @GetMapping("/Staff")
    public String showStaff(HttpSession session) {
        Account account = (Account) session.getAttribute("USER");
        if (account != null && account.getRole().getRoleID() == 3)
            return "admin/admin";
        else
            return "redirect:/";
    }





}
