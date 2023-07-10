package org.jio.fyoga.util;/*  Welcome to Jio word
    @author: Jio
    Date: 7/9/2023
    Time: 6:20 PM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import jakarta.servlet.http.HttpSession;
import org.jio.fyoga.entity.Account;

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


}
