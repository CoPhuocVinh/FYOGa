package org.jio.fyoga.controllers.admin;/*  Welcome to Jio word
    @author: Jio
    Date: 7/13/2023
    Time: 10:53 PM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

@Controller
@RequestMapping("/calender")
public class TestCalender {
    @GetMapping("")
    public String Show(){
        return "admin/TestCalender";
    }

    @PostMapping("")
    public String CreateCalender(Model model){
        Calendar calendar = Calendar.getInstance();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String dateStr = currentDate.format(formatter);

        String[] dateParts = dateStr.split("/");
        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);

        // xu ly
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        // end xu ly
        model.addAttribute("MSG", "tao lich thanh cong");
        return "admin/TestCalender";
    }
}
