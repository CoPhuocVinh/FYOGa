package org.jio.fyoga.controllers.admin;/*  Welcome to Jio word
    @author: Jio
    Date: 7/20/2023
    Time: 10:38 PM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import jakarta.servlet.http.HttpSession;
import org.jio.fyoga.entity.*;
import org.jio.fyoga.entity.Class;
import org.jio.fyoga.model.ActivityCreateOrEditDTO;
import org.jio.fyoga.model.Schedule.ActivityClassDTO;
import org.jio.fyoga.model.Schedule.WeekDTO;
import org.jio.fyoga.model.Schedule.WeekScheduleDTO;
import org.jio.fyoga.service.IClassService;
import org.jio.fyoga.service.IScheduleService;
import org.jio.fyoga.service.ISlotService;
import org.jio.fyoga.service.impl.ActivityClassServiceImpl;
import org.jio.fyoga.service.impl.ScheduleServiceImpl;
import org.jio.fyoga.util.MyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    IClassService classService;

    @Autowired
    ISlotService slotService;

    @GetMapping("")
    public String showSchedule(Model model, HttpSession session
            , @RequestParam(name = "week",required = false, defaultValue = "0") String changeWeek
            , @RequestParam(name = "year",required = false, defaultValue = "0") String changeYear) {

        int weekChange = Integer.parseInt(changeWeek);
        int yearChange = Integer.parseInt(changeYear);

        //scheduleService.createNextWeekScheduleIfNotExists();
        if (weekChange == 0){
            List<WeekDTO> weekDTOS = scheduleServiceTest.WeekNow();
            List<Schedule> schedules = scheduleService.findByYearAndNoWeek(weekDTOS.get(1).getYear(),weekDTOS.get(0).getWeekOfYear());

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

    // /FYoGa/Login/ADMIN/schedule/eventCreateOrEdit
    @GetMapping("/eventCreateOrEdit")
    public String showEventCreateOrEdit(@RequestParam int activityID, Model model,@RequestParam int day
    , @RequestParam int slot){

        ArrayList<Class> classList = (ArrayList<Class>) classService.findAll();
        ActivityCreateOrEditDTO activityClassDTO = new ActivityCreateOrEditDTO();
        if (activityID == 0){
            activityClassDTO.setDayOfWeek(day);
            activityClassDTO.setEdit(false);
            activityClassDTO.setSlotID(slot);
            //activityClassDTO.setSchedule(schedules);
            //activityClassDTO.setDayOfMonth(date);
        }else {
            ActivityClass activityClassEntity = activityClassServiceTest.findById(activityID);
            BeanUtils.copyProperties(activityClassEntity, activityClassDTO);
            activityClassDTO.setEdit(true);
            activityClassDTO.setAClassID(activityClassEntity.getAClass().getClassID());
            activityClassDTO.setSlotID(activityClassEntity.getSlot().getSlotID());
        }

        model.addAttribute("ACTIVITY", activityClassDTO);

        model.addAttribute("CLASSLIST",classList);
        return "admin/addClassOnSchedule";
    }

    @PostMapping("/eventCreateOrEdit")
    public String EventCreateOrEdit(@ModelAttribute(name = "ACTIVITY")  ActivityCreateOrEditDTO ACTIVITY, Model model
            ,  HttpSession session){
        Account account = (Account) session.getAttribute("USER");
        int noWeek = (int) session.getAttribute("WEEKNOW");
        int noYear = (int) session.getAttribute("YEARNOW");
        Schedule schedules = scheduleServiceTest.findScheduleByYearAndNoWeek(noYear,noWeek);
        Date date = MyUtil.getDateFromDayWeekYear(ACTIVITY.getDayOfWeek(),noWeek,noYear) ;
        Slot slotEntity = slotService.findById(ACTIVITY.getSlotID());
        ActivityClass entity = new ActivityClass();
        Class classEntity = classService.findById(ACTIVITY.getAClassID());


        if (ACTIVITY.isEdit()){
            entity = activityClassServiceTest.findById(ACTIVITY.getActivityID());
            entity.setAClass(classEntity);
            entity.setNote(ACTIVITY.getNote());

        }else {
            // copy tu model sang entity
            BeanUtils.copyProperties(ACTIVITY, entity);
            entity.setStatus(1);
            entity.setSlot(slotEntity);
            entity.setStaff(account);
            entity.setSchedule(schedules);
            entity.setDayOfMonth(date);
            entity.setAClass(classEntity);

        }

        activityClassServiceTest.save(entity);


        return "redirect:/FYoGa/Login/ADMIN/schedule";
    }

    @GetMapping("/remove")
    public String remove(@RequestParam int activityID){
        ActivityClass activityClass = activityClassServiceTest.findById(activityID);
        activityClass.setStatus(0);
        activityClassServiceTest.save(activityClass);
        return "redirect:/FYoGa/Login/ADMIN/schedule";
    }




}
