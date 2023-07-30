package org.jio.fyoga.controllers.admin;/*  Welcome to Jio word
    @author: Jio
    Date: 7/28/2023
    Time: 11:34 PM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.Schedule;
import org.jio.fyoga.model.Schedule.WeekDTO;
import org.jio.fyoga.model.Schedule.WeekScheduleDTO;
import org.jio.fyoga.service.impl.ActivityClassServiceImpl;
import org.jio.fyoga.service.impl.ScheduleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/FYoGa/Login/ADMIN/attendance")
public class AAttendance {
    @Autowired
    ScheduleServiceImpl scheduleServiceTest;
    @Autowired
    ActivityClassServiceImpl activityClassServiceTest;

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

    @GetMapping("/check")
    public String showCheck(){


        return "";
    }
}
