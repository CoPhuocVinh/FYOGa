package org.jio.fyoga.controllers.web;/*  Welcome to Jio word
    @author: Jio
    Date: 6/19/2023
    Time: 12:44 AM
    
    ProjectName: demoSpring01
    Jio: I wish you always happy with coding <3
*/

import jakarta.validation.Valid;
import org.jio.fyoga.entity.Account;
import org.jio.fyoga.entity.Role;
import org.jio.fyoga.model.AccountDTO;
import org.jio.fyoga.service.IAccountService;
import org.jio.fyoga.service.IRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Date;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    IAccountService accountService;

    @Autowired
    IRoleService roleService;

    @PostMapping("/registeredUser")
    //@RequestParam Map<String, Object> params,
    public String Register(ModelMap model,
                           @Valid @ModelAttribute("ACCOUNT")AccountDTO account, BindingResult result){
        if(result.hasErrors()){
            return "web/register";
        }
        Account entity = new Account();
        // copy tu model sang entity
        BeanUtils.copyProperties(account, entity);
        Role roleEntity = roleService.findRoleByRoleID(1);
        entity.setRole(roleEntity);
        System.out.println(entity.getEmail());
        Date date = new Date(System.currentTimeMillis());
        entity.setAcceptedDate(date);
        entity.setStatus(1);
        accountService.save(entity);

        System.out.println("register thành công");
        return "web/login";
    }

    public String GetStaff(Model model){
        List<Account> accounts = accountService.findAccountByRole(3);
        model.addAttribute("LISTSTAFF",accounts);
        return "web/";
    }
}
