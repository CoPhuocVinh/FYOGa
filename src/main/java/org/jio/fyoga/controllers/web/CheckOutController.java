package org.jio.fyoga.controllers.web;/*  Welcome to Jio word
    @author: Jio
    Date: 6/30/2023
    Time: 12:45 AM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import jakarta.servlet.http.HttpSession;
import org.jio.fyoga.entity.Account;
import org.jio.fyoga.entity.Discount;
import org.jio.fyoga.entity.Package;
import org.jio.fyoga.entity.Register;
import org.jio.fyoga.model.RegisterDTO;
import org.jio.fyoga.service.IDiscountService;
import org.jio.fyoga.service.IPackageService;
import org.jio.fyoga.service.IRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.sql.Date;
import java.util.*;

@RequestMapping("/FYoGa/Course/PackageCheckOut")
@Controller
public class CheckOutController {

    @Autowired
    IPackageService packageService;
    @Autowired
    IRegisterService registerService;

    @Autowired
    IDiscountService discountService;

    @GetMapping("")
    public String ShowCheckOut(HttpSession session
            , @RequestParam int discountID, Model model
            , @RequestParam int typePaying){

        String url = "web/checkoutCourse";
        Account account = (Account) session.getAttribute("USER");

        if(account == null){
            //System.out.println(packageID);
            session.setAttribute("CHECKOUTING", discountID);
            url = "redirect:/FYoGa/Login";
        }
        if(typePaying == 0 ){
            Optional<Discount> discountEntity = discountService.findById(discountID);
            model.addAttribute("PAYING",discountEntity);
            model.addAttribute("TYPEPAYING", "Thanh toán tại quầy");
        }else {

        }

        // xu ly register thanh cong
        String SUCCESS = (String) session.getAttribute("SUCCESS");
        if (SUCCESS != null){
            model.addAttribute("SUCCESS","SUCCESS");
            session.removeAttribute("SUCCESS");
        }
        return url;
    }

    @PostMapping("/Checkout")
    public String Checkout(HttpSession session, @RequestParam int discountID, Model model){
        Account account = (Account) session.getAttribute("USER");
        //Optional<Package> packageEntiry = packageService.findById(packageID);
        Optional<Discount> discountEntiry = discountService.findById(discountID);

        float price_discount = discountEntiry.get().getAPackage().getPrice() * (100 - discountEntiry.get().getPercentDiscount())/100;
        int slotAvailable = discountEntiry.get().getAPackage().getSlotOnMonth()*discountEntiry.get().getTimeOnMonth();
        int timeAvailable = discountEntiry.get().getTimeOnMonth();

        Date date = new Date(System.currentTimeMillis());
        RegisterDTO registerDTO = RegisterDTO.builder()
                .customerID(account.getAccountID())
                .packageID(discountID)
                .status(2)
                .priceOriginal(discountEntiry.get().getAPackage().getPrice())
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
        //registerEntity.setPackages(packageEntiry.orElseThrow());
        registerEntity.setADiscount(discountEntiry.orElseThrow());

        registerService.save(registerEntity);
        System.out.println("register thành công");
        session.setAttribute("SUCCESS","SUCCESS");
        return "redirect:/FYoGa/Course/PackageCheckOut?discountID=" + discountID+"&typePaying=0";
    }



}
