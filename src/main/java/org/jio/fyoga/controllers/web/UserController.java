package org.jio.fyoga.controllers.web;/*  Welcome to Jio word
    @author: Jio
    Date: 6/19/2023
    Time: 12:44 AM
    
    ProjectName: demoSpring01
    Jio: I wish you always happy with coding <3
*/

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.jio.fyoga.entity.*;
import org.jio.fyoga.entity.Class;
import org.jio.fyoga.model.AccountDTO;
import org.jio.fyoga.model.BookingDTO;
import org.jio.fyoga.model.ClassDTO;
import org.jio.fyoga.service.*;
import org.jio.fyoga.util.MyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/FYoGa/Login/User")
@Controller
public class UserController {

    @Autowired
    IAccountService accountService;

    @Autowired
    IRoleService roleService;

    @Autowired
    IRegisterService registerService;

    @Autowired
    ICourseService courseService;

    @Autowired
    IClassService classService;

    @Autowired
    IBookingService bookingService;

    public String GetStaff(Model model) {
        List<Account> accounts = accountService.findAccountByRole(3);
        model.addAttribute("LISTSTAFF", accounts);
        return "web/";
    }

    @GetMapping("/HistoryPay")
    public String Ristorypage(HttpSession session, Model model) {
        if (MyUtil.checkAuthen(session)) {
            Account account = (Account) session.getAttribute("USER");

        // xủ lý code trong đây
            // chưa thanh toán
            List<Register> registers_paying = new ArrayList<>();
            registers_paying = registerService.findByCustomer_AccountIDAndStatus(account.getAccountID(),2);
            model.addAttribute(("REGISTER_PAYING"), registers_paying);

            // thanh toan xong
            List<Register> registers_payed = new ArrayList<>();
            registers_paying = registerService.findByCustomer_AccountIDAndStatus(account.getAccountID(),1);
            model.addAttribute(("REGISTER_PAYED"), registers_payed);
            return "web/historyPay";
        }
        return "web/login";
    }

    @GetMapping("/EditInfor")
    public String Editpage(HttpSession session, Model model) {
        if (MyUtil.checkAuthen(session)) {
        // xủ lý code trong đây
            Account accountEntity = (Account) session.getAttribute("USER");
            AccountDTO accountDTO = new AccountDTO();
            BeanUtils.copyProperties(accountEntity, accountDTO);
            accountDTO.setGender(accountDTO.getGender().trim());
            accountDTO.setPhone(accountDTO.getPhone().trim());
            model.addAttribute("USER", accountDTO);
            return "web/user-editInfo";
        }
        return "web/login";
    }

    @PostMapping("/EditInfor")
    public String EditUser(HttpSession session, RedirectAttributes ra,@ModelAttribute AccountDTO accountDTO) {
        if (MyUtil.checkAuthen(session)) {
            // xủ lý code trong đây
            Account accountEntity = (Account) session.getAttribute("USER");
            accountEntity.setGender(accountDTO.getGender());
            accountEntity.setPhone(accountDTO.getPhone());
            accountEntity.setFullName(accountDTO.getFullName());
            //BeanUtils.copyProperties(accountDTO, accountEntity);
            accountService.save(accountEntity);
            ra.addFlashAttribute("MSG", "The user has been update successfully.");

            return "redirect:/FYoGa/Login/User/EditInfor";
        }
        return "web/login";
    }


    @GetMapping("/ChangePass")
    public String ChangePass(HttpSession session,Model model) {
        if (MyUtil.checkAuthen(session)) {
        // xủ lý code trong đây
            Account accountEntity = (Account) session.getAttribute("USER");
            AccountDTO accountDTO = new AccountDTO();
            BeanUtils.copyProperties(accountEntity, accountDTO);
            model.addAttribute("USER", accountDTO);
            return "web/user-editPass";
        }
        return "web/login";
    }

    @PostMapping("/ChangePass")
    public String ChangePassUser(HttpSession session, RedirectAttributes ra,@ModelAttribute AccountDTO accountDTO) {
        if (MyUtil.checkAuthen(session)) {
            // xủ lý code trong đây
            Account accountEntity = (Account) session.getAttribute("USER");
            if(accountEntity.getPassword().equals(accountDTO.getPassword())){
                accountEntity.setPassword(accountDTO.getNewPassword());
                accountService.save(accountEntity);
                ra.addFlashAttribute("MSG", "The password update successfully !!!.");
            }else {
                ra.addFlashAttribute("MSG", "The password incorrect !!!.");

            }



            return "redirect:/FYoGa/Login/User/ChangePass";
        }
        return "web/login";
    }
    // /FYoGa/Login/User/ScheduleClass
    @GetMapping("/ScheduleClass")
    public String ScheduleClass(HttpSession session, Model model, @RequestParam int courseID) {
        if (MyUtil.checkAuthen(session)) {
            // xủ lý code trong đây
            Course course = courseService.findById(courseID).orElseThrow();
            model.addAttribute("COURSE",course);
            List<Class> classList = classService.findClassByCourse_CourseID(courseID);
            model.addAttribute("LISTCLASS", classList);

            BookingDTO bookingDTO = BookingDTO.builder().build();
            model.addAttribute("BOOKING",bookingDTO);
            return "web/scheduleDetail";
        }
        return "web/login";
    }
    // /FYoGa/Login/User/ScheduleClass/Booking
    @PostMapping("/Booking")
    public String booking (HttpSession session, @ModelAttribute("BOOKING") BookingDTO bookingDTO, RedirectAttributes ra){
        Account accountEntity = (Account) session.getAttribute("USER");
        Class classEntity = classService.findById(bookingDTO.getClassID());
        Booking bookingEntity = new Booking();
        if(bookingService.findByaClassBooking_ClassIDAndCustomer_AccountID(
                bookingDTO.getClassID(),accountEntity.getAccountID())!= null){

            ra.addFlashAttribute("MSG", "Bạn đăng ký không thành công vì bạn đã đăng ký lớp này");


        }else {
            Date date = new Date(System.currentTimeMillis());
            bookingEntity.setBookingDate(date);
            bookingEntity.setStatus(1);
            bookingEntity.setCustomer(accountEntity);
            bookingEntity.setAClassBooking(classEntity);
            bookingService.save(bookingEntity);
            ra.addFlashAttribute("MSG", "Bạn đã đăng ký lớp thành công !!!");
        }


        return "redirect:/FYoGa/Login/User/ScheduleClass?courseID="+ classEntity.getCourse().getCourseID();
    }
}

