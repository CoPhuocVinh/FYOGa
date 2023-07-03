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
import org.jio.fyoga.entity.Register;
import org.jio.fyoga.model.PackageDTO;
import org.jio.fyoga.model.RegisterDTO;
import org.jio.fyoga.service.IPackageService;
import org.jio.fyoga.service.IRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.Optional;
@RequestMapping("/FYoGa/Course/PackageCheckOut")
@Controller
public class CheckOutController {

    @Autowired
    IPackageService packageService;
    @Autowired
    IRegisterService registerService;

    @GetMapping("")
    public String ShowCheckOut(HttpSession session, @RequestParam int packageID, Model model){
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
        String SUCCESS = (String) session.getAttribute("SUCCESS");
        if (SUCCESS != null){
            model.addAttribute("SUCCESS","SUCCESS");
            session.removeAttribute("SUCCESS");
        }
        return url;
    }

    @PostMapping("/Checkout")
    public String Checkout(HttpSession session, @RequestParam int packageID, Model model){
        Account account = (Account) session.getAttribute("USER");
        Optional<Package> packageEntiry = packageService.findById(packageID);
        float price_discount = packageEntiry.get().getPrice() * (100 - packageEntiry.get().getPercentDiscount())/100;
        int slotAvailable = packageEntiry.get().getSlotOnMonth()*packageEntiry.get().getTimeOnMonth();
        int timeAvailable = packageEntiry.get().getTimeOnMonth();

        Date date = new Date(System.currentTimeMillis());
        RegisterDTO registerDTO = RegisterDTO.builder()
                .customerID(account.getAccountID())
                .packageID(packageID)
                .status(2)
                .priceOriginal(packageEntiry.get().getPrice())
                .priceDiscount(price_discount)
                .slotAvailable(slotAvailable)
                .timeAvailable(timeAvailable)
                .slotUsed(slotAvailable)
                .registeredDate(date)
                .weekUsed(timeAvailable*4)
                .build();

        Register registerEntity = new Register();
        BeanUtils.copyProperties(registerDTO,registerEntity);
        registerEntity.setCustomer(account);
        registerEntity.setPackages(packageEntiry.orElseThrow());

        registerService.save(registerEntity);
        System.out.println("register thành công");
        session.setAttribute("SUCCESS","SUCCESS");
        return "redirect:/FYoGa/Course/PackageCheckOut?packageID=" + packageID;
    }


}
