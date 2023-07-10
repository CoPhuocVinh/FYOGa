package org.jio.fyoga.controllers.web;/*  Welcome to Jio word
    @author: Jio
    Date: 6/10/2023
    Time: 11:50 PM
    
    ProjectName: demoSpring
    Jio: I wish you always happy with coding <3
*/

import jakarta.servlet.http.HttpSession;
import org.jio.fyoga.entity.Account;
import org.jio.fyoga.entity.Course;
import org.jio.fyoga.model.AccountDTO;
import org.jio.fyoga.service.IAccountService;
import org.jio.fyoga.service.ICourseService;
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

@Controller
public class HomepageController {

    @Autowired
    ICourseService courseService;
    @Autowired
    IAccountService accountService;

    @RequestMapping(value = {"", "/",})
    public String showHomepage(){
        return "redirect:/FYoGa";
    }


    @RequestMapping(value = { "/FYoGa", "/FYoGa/"})
    public String homePage(Model model) {
            model.addAttribute("COURSES", courseService.findAll());
            model.addAttribute("STAFFS", accountService.findAccountByRole(2));

        return "web/index";
    }





    //xu ly COURSE

    @RequestMapping("/FYoGa/Logout")
    public String Logout(HttpSession session) {

        session.removeAttribute("USER");
        return "redirect:/";
    }

    @GetMapping("/FYoGa/forgotPass")
    public String ForgotPass(){
        return "web/forgotPass";
    }

    @PostMapping("/FYoGa/forgotPass")
    public String ForgotPass(@RequestParam Map<String, Object> params){
        String password = (String) params.get("password");
        return "web/forgotPass";
    }





}
