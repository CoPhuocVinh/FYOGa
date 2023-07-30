package org.jio.fyoga.controllers.web;/*  Welcome to Jio word
    @author: Jio
    Date: 6/10/2023
    Time: 11:50 PM
    
    ProjectName: demoSpring
    Jio: I wish you always happy with coding <3
*/

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jio.fyoga.entity.Account;
import org.jio.fyoga.entity.Course;
import org.jio.fyoga.entity.Schedule;
import org.jio.fyoga.model.AccountDTO;
import org.jio.fyoga.model.Schedule.WeekDTO;
import org.jio.fyoga.model.Schedule.WeekScheduleDTO;
import org.jio.fyoga.service.IAccountService;
import org.jio.fyoga.service.ICourseService;
import org.jio.fyoga.service.IGmailService;
import org.jio.fyoga.service.IScheduleService;
import org.jio.fyoga.service.impl.ActivityClassServiceImpl;
import org.jio.fyoga.service.impl.ScheduleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
public class HomepageController {

    @Autowired
    ICourseService courseService;
    @Autowired
    IAccountService accountService;

    @Autowired
    IGmailService gmailService;

    @Autowired
    IScheduleService scheduleService;
    @Autowired
    ScheduleServiceImpl scheduleServiceTest;
    @Autowired
    ActivityClassServiceImpl activityClassServiceTest;

    @RequestMapping(value = {"", "/",})
    public String showHomepage(){
        return "redirect:/FYoGa";
    }


    @RequestMapping(value = { "/FYoGa", "/FYoGa/"})
    public String homePage(Model model) {
            model.addAttribute("COURSES", courseService.findAll());
            model.addAttribute("STAFFS", accountService.findAccountByRole(2));

            List<WeekDTO> weekDTOS = scheduleServiceTest.WeekNow();


            model.addAttribute("EVENTS",weekDTOS);
            int noWeek = weekDTOS.get(1).getWeekOfYear();
            int noYear =  weekDTOS.get(1).getYear();
            Schedule schedules = scheduleServiceTest.findScheduleByYearAndNoWeek(noYear,noWeek);
            List<WeekScheduleDTO> weekScheduleDTOs = activityClassServiceTest.getActivityClassesFromMondayToSaturday(schedules.getScheduleID());
            System.out.printf("bibi");
            model.addAttribute("ACTIVITYS",weekScheduleDTOs);
        return "web/index";
    }





    //xu ly COURSE

    @RequestMapping("/FYoGa/Logout")
    public String Logout(HttpSession session,
    HttpServletRequest request, Authentication authentication) {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, null, authentication);
        }
        try {
            if (session != null && !session.isNew()) {
                session.removeAttribute("USER");
            }
        } catch (IllegalStateException e) {
            // Session already invalidated
            // Handle the exception as needed
            // For example, you can log the exception or perform any other desired action
        }
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


    @PostMapping("/forgotPassword")
    public String InputEmail(@RequestParam String email4got, HttpSession session, RedirectAttributes ra) {
//        if(!accountService.existsByEmail(email4got)){
//            ra.addFlashAttribute("MSG", "Email doesn't exist. Please register new account!");
//            return "redirect:/FYoGa/forgotPass";
//        }
        session.setAttribute("EMAILVERIFY", email4got);
        gmailService.sendVerificationEmail(email4got);
        //return "redirect:/FYoGa/forgotPass";
        return "redirect:/FYoGa/verify";
    }

    @GetMapping("/FYoGa/verify")
    public String showVerify(HttpSession session,Model model){
        return "web/verify";
    }



    @PostMapping("/verify")
    public String Code(@RequestParam("verifyCode") String verifyCode, RedirectAttributes ra,
                       HttpSession session) {

        String email = (String) session.getAttribute("EMAILVERIFY");
        if (gmailService.verifyCodeIsValid(verifyCode)) {
            // Mã verify code hợp lệ, thực hiện xác thực tài khoản tại đây

            //user.setStatus(true);
            //userRepository.save(user);
            // Thông báo cho người dùng rằng tài khoản đã được xác thực thành công
            ra.addFlashAttribute("MSG", "Account has been successfully verified!");
            //return "redirect:/confirmpassword";
            //return "web/changPass";
            return "redirect:/FYoGa/Confirmpassword";
        } else {
            // Mã verify code không hợp lệ hoặc đã hết hạn
            // Thông báo cho người dùng biết rằng mã không hợp lệ
            ra.addFlashAttribute("MSG",
                    "The authentication code is invalid or has expired. Please check again or request to resend the code.");
            return "redirect:/FYoGa/verify";
            //return "web/verify";

        }


    }

    @GetMapping("/FYoGa/Confirmpassword")
    public String showConfirmpassword(HttpSession session,Model model){
        return "web/changPass";
    }

    @PostMapping("/FYoGa/Confirmpassword")
    public String Confirmpassword(@RequestParam String password,HttpSession session, RedirectAttributes ra){
        String email = (String) session.getAttribute("EMAILVERIFY");
        session.removeAttribute("EMAILVERIFY");
        Account user = accountService.findAccountByEmail(email);
        user.setPassword(password);
        accountService.save(user);
        ra.addFlashAttribute("MSG", "Account has been successfully verified!");
        return "redirect:/FYoGa/Login";
    }


}
