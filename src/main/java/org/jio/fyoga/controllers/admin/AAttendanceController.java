package org.jio.fyoga.controllers.admin;/*  Welcome to Jio word
    @author: Jio
    Date: 7/28/2023
    Time: 11:34 PM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import jakarta.servlet.http.HttpSession;
import org.jio.fyoga.entity.*;
import org.jio.fyoga.model.AttendanceDTO;
import org.jio.fyoga.model.Schedule.WeekDTO;
import org.jio.fyoga.model.Schedule.WeekScheduleDTO;
import org.jio.fyoga.service.IAttendanceService;
import org.jio.fyoga.service.IBookingService;
import org.jio.fyoga.service.IRegisterService;
import org.jio.fyoga.service.impl.ActivityClassServiceImpl;
import org.jio.fyoga.service.impl.ScheduleServiceImpl;
import org.jio.fyoga.util.MyCheckExpired;
import org.jio.fyoga.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/FYoGa/Login/ADMIN/Attendance")
public class AAttendanceController {
    @Autowired
    ScheduleServiceImpl scheduleServiceTest;
    @Autowired
    ActivityClassServiceImpl activityClassServiceTest;
    @Autowired
    IBookingService bookingService;

    @Autowired
    IAttendanceService attendanceService;

    @Autowired
    IRegisterService registerService;

    @GetMapping("")
    public String show(Model model){


        // xu ly schedule
        List<WeekDTO> weekDTOS = scheduleServiceTest.WeekNow();

        model.addAttribute("EVENTS",weekDTOS);
        int noWeek = weekDTOS.get(1).getWeekOfYear();
        int noYear =  weekDTOS.get(1).getYear();
        Schedule schedules = scheduleServiceTest.findScheduleByYearAndNoWeek(noYear,noWeek);
        List<WeekScheduleDTO> weekScheduleDTOs = activityClassServiceTest.getActivityClassesFromMondayToSaturday(schedules.getScheduleID());
        System.out.printf("bibi");
        model.addAttribute("ACTIVITYS",weekScheduleDTOs);
        // end xu ly schedule
        return "admin/attendance";
    }

    @GetMapping("/Check")
    public String showCheck(@RequestParam int classID,
                            @RequestParam int activityID,
                            HttpSession session, Model model){
        // xu ly code
        Account account = (Account) session.getAttribute("USER");
        //List<Booking> bookingsOn = bookingService.findAllByAClassBooking_ClassIDAndStatus(classID,1);
        //List<AttendanceDTO>
        List<Attendance> attendances = attendanceService.findAllByActivityClass_ActivityID(activityID);
        if (attendances.size() > 0){
            List<Attendance> attendancesON = attendanceService.findAllByActivityClass_ActivityIDAndIsPresent(activityID,true);
            model.addAttribute("ATTENON",attendancesON);
        }else {
            List<Booking> bookings = bookingService.findAllByAClassBooking_ClassIDAndStatus(classID,1);
            attendances = new ArrayList<>();
            ActivityClass activityClass = activityClassServiceTest.findById(activityID);
            if (bookings.size() > 0){
                for(Booking booking : bookings){
                    Attendance attendance = new Attendance();
                    attendance.setCustomer(booking.getCustomer());
                    attendance.setActivityClass(activityClass);
                    attendance.setStaff(account.getAccountID());
                    attendances.add(attendance);
                    attendance.setIsPresent(false);
                }
                attendanceService.saveAll(attendances);
            }
        }
        List<Attendance> attendancesOFF = attendanceService.findAllByActivityClass_ActivityIDAndIsPresent(activityID,false);

        model.addAttribute("ATTENOFF",attendancesOFF);




        return "admin/checkattendance";
    }

    @GetMapping("/Check/True")
    public String attendance(@RequestParam int AttendanceID, RedirectAttributes ra){

        List<Register> registerList = registerService.findAllByOrderByRegisteredDateDesc();
        MyCheckExpired.checkExpiredOnRegister(registerList);
        registerService.saveAll(registerList);

        Attendance attendance = attendanceService.findById(AttendanceID).orElse(null);
        if (attendance != null){
            Register register02 = null;
            Register register03 = null;
            Register register01 = null;
            int courseID = attendance.getActivityClass().getAClass().getCourse().getCourseID();
            int userID= attendance.getCustomer().getAccountID();
            try{
                register03 = registerService.findRegisterByStatusAndcourseID(3,courseID,userID);
            }catch (Exception ex){

            }
            try{
                register02 = registerService.findFirstByStatusAndCourseIDOrderByRegisteredDateAsc(2,courseID,userID);
            }catch (Exception ex){

            }
            try{
                register01 = registerService.findFirstByStatusAndCourseIDOrderByRegisteredDateAsc(1,courseID,userID);
            }catch (Exception ex){

            }
            if(checkRegister03(register03)){
                ra.addFlashAttribute("MSG","điểm danh ( "+attendance.getCustomer().getFullName()+ " ) thành công");
                attendance.setIsPresent(true);
                attendanceService.save(attendance);
            }
            else {
                if(register02 != null){
                    register02.setStatus(3);
                    registerService.save(register02);
                    checkRegister03(register02);
                    ra.addFlashAttribute("MSG","điểm danh ( "+attendance.getCustomer().getFullName()+ " ) thành công");
                    attendance.setIsPresent(true);
                    attendanceService.save(attendance);
                }
                else {
                    if (register01 != null){
                        ra.addFlashAttribute("MSG","Người dùng chưa thanh toán gói nào mời người dùng thanh toán( "+attendance.getCustomer().getFullName()+ " )");

                    }else {
                        List<Booking> bookings =bookingService.findAllByaClassBooking_Course_CourseIDAndCustomer_AccountIDAndStatus(courseID,userID,1);
                        for(Booking booking : bookings){
                            booking.setStatus(0);
                        }
                        ra.addFlashAttribute("MSG","Người dùng chưa có gói nào mời người dùng đăng ký( "+attendance.getCustomer().getFullName()+ " )");
                    }
                }
            }


        }
        int classID = attendance.getActivityClass().getAClass().getClassID();

        return "redirect:/FYoGa/Login/ADMIN/Attendance/Check?classID="+classID+"&activityID="+attendance.getActivityClass().getActivityID();
    }

    public boolean checkRegister03(Register register03){
        boolean check = false;
        if (register03 != null){
            int slotUsed = register03.getSlotUsed();
            if ((slotUsed -1 ) == 0){
                register03.setSlotUsed(0);
                register03.setStatus(0);
            }else {

                register03.setSlotUsed(slotUsed -1);

            }
            check = true;
            registerService.save(register03);
        }
        return check;
    }
}
