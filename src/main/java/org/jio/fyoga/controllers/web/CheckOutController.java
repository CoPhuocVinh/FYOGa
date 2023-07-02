package org.jio.fyoga.controllers.web;/*  Welcome to Jio word
    @author: Jio
    Date: 6/30/2023
    Time: 12:45 AM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import jakarta.servlet.http.HttpSession;
import org.jio.fyoga.entity.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CheckOutController {

    @GetMapping("/FYoGa/Course/PackageCheckOut")
    public String checkOut(HttpSession session, @RequestParam int packageID){
        String url = "forward:/FYoGa/Login";
        Account account = (Account) session.getAttribute("USER");
        if(account != null){
            System.out.println(packageID);

            url = "web/checkoutCourse";
        }
        return url;
    }
}
