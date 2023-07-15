package org.jio.fyoga.controllers.admin;

import org.jio.fyoga.entity.Package;
import org.jio.fyoga.service.IPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/FYoGa/Login/ADMIN/Package")
public class APackageController {

    @Autowired
    IPackageService packageService;

    @GetMapping("")
    public String getPackages(Model model){
        List<Package> packageList = packageService.findAll();
        model.addAttribute("PACKAGELIST", packageList);
        return "admin/page_discount";
    }


}
