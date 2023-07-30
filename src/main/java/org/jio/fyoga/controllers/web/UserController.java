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
import org.jio.fyoga.model.Schedule.WeekDTO;
import org.jio.fyoga.model.Schedule.WeekScheduleDTO;
import org.jio.fyoga.service.*;
import org.jio.fyoga.service.impl.ActivityClassServiceImpl;
import org.jio.fyoga.service.impl.ScheduleServiceImpl;
import org.jio.fyoga.util.MyCheckExpired;
import org.jio.fyoga.util.MyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Date;
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

    @Autowired
    ScheduleServiceImpl scheduleServiceTest;
    @Autowired
    ActivityClassServiceImpl activityClassServiceTest;


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
            if(accountDTO.getGender() != null && accountDTO.getPhone() != null){
                accountDTO.setGender(accountDTO.getGender().trim());
                accountDTO.setPhone(accountDTO.getPhone().trim());
            }

            model.addAttribute("USER", accountDTO);
            return "web/user-editInfo";
        }
        return "web/login";
    }

    @PostMapping("/EditInfor")
    public String EditUser(HttpSession session, RedirectAttributes ra,@ModelAttribute AccountDTO accountDTO
            , @RequestParam("file") MultipartFile file) {
        if (MyUtil.checkAuthen(session)) {
            // xủ lý code trong đây
            Account accountEntity = (Account) session.getAttribute("USER");
            accountEntity.setGender(accountDTO.getGender());
            accountEntity.setPhone(accountDTO.getPhone());
            accountEntity.setFullName(accountDTO.getFullName());

            try {
                accountService.saveIMGAccount(file, accountEntity);
            } catch (IOException e) {
                // Xử lý lỗi nếu cần
            }

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
            checkExpiredOnUser(session, courseID);

            Account account = (Account) session.getAttribute("USER");

            Course course = courseService.findById(courseID).orElseThrow();
            model.addAttribute("COURSE",course);
            List<Class> classList = classService.findClassByCourse_CourseID(courseID);
            model.addAttribute("LISTCLASS", classList);

            BookingDTO bookingDTO = BookingDTO.builder().build();
            model.addAttribute("BOOKING",bookingDTO);

            List<WeekDTO> weekDTOS = scheduleServiceTest.WeekNow();

            model.addAttribute("EVENTS",weekDTOS);
            int noWeek = weekDTOS.get(1).getWeekOfYear();
            int noYear =  weekDTOS.get(1).getYear();
            Schedule schedules = scheduleServiceTest.findScheduleByYearAndNoWeek(noYear,noWeek);
            List<WeekScheduleDTO> weekScheduleDTOs = activityClassServiceTest.getActivityClassesFromMondayToSaturdayOnCourse(schedules.getScheduleID(),courseID,account.getAccountID());
            System.out.printf("bibi");
            model.addAttribute("ACTIVITYS",weekScheduleDTOs);
            model.addAttribute("EXPIRED", weekScheduleDTOs.get(0).getDayOfWeeks().get(0).getExpired());

            return "web/scheduleDetail";
        }
        return "web/login";
    }
    // /FYoGa/Login/User/Booking
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

            int courseID = bookingEntity.getAClassBooking().getCourse().getCourseID();

            Register register02 = registerService.findTopByStatusAndCourseIDOrderByRegisteredDateDesc(2,courseID);
            Register register03 = registerService.findRegisterByStatusAndcourseID(3,courseID);
            if(register02 != null){
                bookingEntity.setExpired(register02.getExpired());
            }else {
                if (register03 != null){
                    bookingEntity.setExpired(register03.getExpired());

                }else {
                    Date dateExpired = MyUtil.expiredDateOnDate(5);
                    bookingEntity.setExpired(dateExpired);
                }
            }


            bookingService.save(bookingEntity);
            ra.addFlashAttribute("MSG", "Bạn đã đăng ký lớp thành công !!!");
        }


        return "redirect:/FYoGa/Login/User/ScheduleClass?courseID="+ classEntity.getCourse().getCourseID();
    }

    @RequestMapping("/downloads-png")
    public ResponseEntity<?> downloadPngCourse(@RequestParam(defaultValue = "") int accountID) {
        byte[] pngData = accountService.getIMGById(accountID);
        if (pngData != null) {
            ByteArrayResource resource = new ByteArrayResource(pngData);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=image.png")
                    .contentType(MediaType.IMAGE_PNG)
                    .body( resource);
        }
        // Xử lý trường hợp tệp tin không tồn tại
        return ResponseEntity.notFound().build();
    }

    public void checkExpiredOnUser(HttpSession session, int courseID){
        // check expired
        Account account = (Account) session.getAttribute("USER");
        List<Booking> bookings = bookingService.findAllByCustomer_AccountID(account.getAccountID());
        bookings = MyCheckExpired.checkExpiredOnBooking(bookings);
        bookingService.saveAll(bookings);

        List<Register> registers = registerService.findAllByByCustomer_AccountID(account.getAccountID());
        registers = MyCheckExpired.checkExpiredOnRegister(registers);
        registerService.saveAll(registers);

        Register register02 = registerService.findTopByStatusAndCourseIDOrderByRegisteredDateDesc(2,courseID);
        Register register03 = registerService.findRegisterByStatusAndcourseID(3,courseID);
        for (Booking booking : bookings){
            if(register02 != null){
                booking.setExpired(register02.getExpired());
            }else {
                if (register03 != null){
                    booking.setExpired(register03.getExpired());

                }else {
                    Date dateExpired = MyUtil.expiredDateOnDate(5);
                    booking.setExpired(dateExpired);
                }
            }
        }


    }
}

