package org.jio.fyoga.util;/*  Welcome to Jio word
    @author: Jio
    Date: 7/9/2023
    Time: 6:20 PM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import jakarta.servlet.http.HttpSession;
import org.jio.fyoga.entity.Account;

import java.util.Calendar;
import java.util.Date;

public class MyUtil {
    public static String Authen(HttpSession session) {
        Account account = (Account) session.getAttribute("USER");

        if (account != null) {
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
        }
        return "web/login";
    }

    public static Boolean checkAuthen(HttpSession session) {
        Account account = (Account) session.getAttribute("USER");
        boolean checkAuthen = false;
        if (account != null) {
            //model.addAttribute("USER", account);
           checkAuthen = true;
        }
        return checkAuthen;
    }

    public static Date getDateFromDayWeekYear(int dayOfWeek, int weekNumber, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.WEEK_OF_YEAR, weekNumber);
        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);

        // Vì ngày bắt đầu của tuần trong Calendar là Chủ nhật (1) và yêu cầu là Thứ 2 (2)
        // Nếu là Chủ nhật thì trả về ngày đầu tiên trong tuần là Thứ 2 của tuần đó
        if (dayOfWeek == Calendar.SUNDAY) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }

        return calendar.getTime();
    }


}
