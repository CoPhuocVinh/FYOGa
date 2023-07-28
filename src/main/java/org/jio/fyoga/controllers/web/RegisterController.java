package org.jio.fyoga.controllers.web;/*  Welcome to Jio word
    @author: Jio
    Date: 7/9/2023
    Time: 6:04 PM
    
    ProjectName: FYoGa
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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
@Controller
@RequestMapping("/FYoGa/registerFYoGa")
public class RegisterController {


    @Autowired
    IAccountService accountService;

    @Autowired
    IRoleService roleService;

    @RequestMapping("")
    public String ShowRegister(ModelMap model) {
        AccountDTO account = new AccountDTO();
        account.setIsEdit(false);
        model.addAttribute("ACCOUNT", account);
        return "web/register";
    }
    @PostMapping("")
    //@RequestParam Map<String, Object> params,
    public String Register(ModelMap model,
                           @Valid @ModelAttribute("ACCOUNT")AccountDTO account, BindingResult result, RedirectAttributes ra){
        if(result.hasErrors()){
            return "web/register";
        }

        Account accountExist;

        Account entity = new Account();
        accountExist = accountService.findAccountByEmail(account.getEmail());
        if (accountExist != null){
            ra.addFlashAttribute("MSG","Email is exist!!!");
            return "redirect:/FYoGa/registerFYoGa";
        }

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
}
