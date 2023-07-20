package org.jio.fyoga.controllers.admin;/*  Welcome to Jio word
    @author: Jio
    Date: 7/20/2023
    Time: 10:38 PM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import jakarta.servlet.http.HttpSession;
import org.jio.fyoga.entity.Schedule;
import org.jio.fyoga.model.Schedule.WeekDTO;
import org.jio.fyoga.model.Schedule.WeekScheduleDTO;
import org.jio.fyoga.service.IScheduleService;
import org.jio.fyoga.service.impl.ActivityClassServiceImpl;
import org.jio.fyoga.service.impl.ScheduleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/FYoGa/Login/ADMIN/schedule")
public class AScheleduController {
    @Autowired
    IScheduleService scheduleService;
    @Autowired
    ScheduleServiceImpl scheduleServiceTest;

    @Autowired
    ActivityClassServiceImpl activityClassServiceTest;

    @GetMapping("")
    public String showSchedule(Model model, HttpSession session
            , @RequestParam(name = "week",required = false, defaultValue = "0") String changeWeek
            , @RequestParam(name = "year",required = false, defaultValue = "0") String changeYear) {

        int weekChange = Integer.parseInt(changeWeek);
        int yearChange = Integer.parseInt(changeYear);

        //scheduleService.createNextWeekScheduleIfNotExists();
        if (weekChange == 0){
            List<WeekDTO> weekDTOS = scheduleServiceTest.WeekNow();
            List<Schedule> schedules = scheduleService.findByYearAndNoWeek(weekDTOS.get(1).getYear(),weekDTOS.get(1).getWeekOfYear());

            model.addAttribute("EVENTS",weekDTOS);
            session.setAttribute("WEEKNOW", weekDTOS.get(1).getWeekOfYear());
            session.setAttribute("YEARNOW", weekDTOS.get(1).getYear());

        }else {
            int noWeek = (int) session.getAttribute("WEEKNOW");
            int noWeekChange = noWeek + weekChange;

            List<WeekDTO> weekDTOS = scheduleServiceTest.WeekChange(noWeekChange);
            model.addAttribute("EVENTS",weekDTOS);
            session.setAttribute("WEEKNOW", weekDTOS.get(1).getWeekOfYear());
            session.setAttribute("YEARNOW", weekDTOS.get(1).getYear());
        }
        int noWeek = (int) session.getAttribute("WEEKNOW");
        int noYear = (int) session.getAttribute("YEARNOW");
        Schedule schedules = scheduleServiceTest.findScheduleByYearAndNoWeek(noYear,noWeek);
        List<WeekScheduleDTO> weekScheduleDTOs = activityClassServiceTest.getActivityClassesFromMondayToSaturday(schedules.getScheduleID());
        System.out.printf("bibi");
        model.addAttribute("ACTIVITYS",weekScheduleDTOs);

        return "admin/setup-calender";
    }


}
