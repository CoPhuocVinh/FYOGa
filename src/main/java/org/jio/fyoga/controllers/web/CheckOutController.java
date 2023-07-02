package org.jio.fyoga.controllers.web;/*  Welcome to Jio word
    @author: Jio
    Date: 6/30/2023
    Time: 12:45 AM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import jakarta.servlet.http.HttpSession;
import org.jio.fyoga.entity.Account;
import org.jio.fyoga.entity.Package;
import org.jio.fyoga.model.PackageDTO;
import org.jio.fyoga.service.IPackageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class CheckOutController {

    @Autowired
    IPackageService packageService;

    @GetMapping("/FYoGa/Course/PackageCheckOut")
    public String checkOut(HttpSession session, @RequestParam int packageID, Model model){
        String url = "web/checkoutCourse";
        Account account = (Account) session.getAttribute("USER");

        if(account == null){
            //System.out.println(packageID);
            url = "redirect:/FYoGa/Login";
        }
        Optional<Package> packageEntiry = packageService.findById(packageID);
//        PackageDTO packageDTO = new PackageDTO();
//        BeanUtils.copyProperties(packageEntiry, packageDTO);
//        float priceDiscount = packageDTO.getPrice() * (100 - packageDTO.getPercentDiscount())/100 ;
//        packageDTO.setPriceDiscount(priceDiscount);
        model.addAttribute("PAYING",packageEntiry);

        return url;
    }
}
