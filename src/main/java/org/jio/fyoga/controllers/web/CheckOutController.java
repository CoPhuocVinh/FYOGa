package org.jio.fyoga.controllers.web;/*  Welcome to Jio word
    @author: Jio
    Date: 6/30/2023
    Time: 12:45 AM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckOutController {

    @GetMapping("/FYoGa/Course/PackageCheckOut")
    public String checkOut(){
        return "web/checkoutCourse";
    }
}
