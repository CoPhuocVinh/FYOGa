package org.jio.fyoga.controllers.admin;/*  Welcome to Jio word
    @author: Jio
    Date: 7/12/2023
    Time: 4:04 PM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.Account;
import org.jio.fyoga.entity.Role;
import org.jio.fyoga.model.AccountDTO;
import org.jio.fyoga.service.IAccountService;
import org.jio.fyoga.service.IRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.util.List;

@RequestMapping("/FYoGa/Login/ADMIN/User")
@Controller
public class AUserController {

    @Autowired
    IAccountService accountService;

    @Autowired
    IRoleService roleService;

    @GetMapping("")
    public String showpageUser(@RequestParam int roleID, Model model) {

        List<Account> accountsON = accountService.findAccountByRole_RoleIDAndStatus(roleID, 1);
        List<Account> accountsOFF = accountService.findAccountByRole_RoleIDAndStatus(roleID, 0);
        model.addAttribute("LISTACCOUNTS_ON", accountsON);
        model.addAttribute("LISTACCOUNTS_OFF", accountsOFF);

        model.addAttribute("ROLEID", roleID);
        return "admin/page_list_teacher";
    }

    // /FYoGa/Login/ADMIN/User/CreateOrUpdate
    @GetMapping("/CreateOrUpdate")
    public String ShowUserOrUpdate(@RequestParam int roleID,
                               @RequestParam int isEdit,
                               @RequestParam(name = "AccountID",required = false, defaultValue = "-1") String R_AccountID,
                               Model model) {
        int AccountID = Integer.parseInt(R_AccountID);
        System.out.println(AccountID);
        AccountDTO account = AccountDTO.builder().build();

        //xu ly update
        if(isEdit == 1 && AccountID >= 0){
            Account accountEntity = accountService.findById(AccountID);
            BeanUtils.copyProperties(accountEntity, account);
            account.setIsEdit(true);
            account.setRoleID(roleID);

        }
        //xu ly CREATE
        if(isEdit == 0){
            account = AccountDTO.builder()
                    .roleID(roleID)
                    .status(1)
                    .build();
            account.setIsEdit(false);


        }

        model.addAttribute("ACCOUNT", account);
        model.addAttribute("ROLEID", roleID);

        return "admin/createnewTeacher";
    }

    // /FYoGa/Login/ADMIN/User/CreateOrUpdate
    @PostMapping("/CreateOrUpdate")
    public String UserOrUpdate (@ModelAttribute("ACCOUNT")AccountDTO account, RedirectAttributes ra){
        Account entity = new Account();
        if(account.getIsEdit()){
            entity = accountService.findById(account.getAccountID());
            entity.setFullName(account.getFullName());
            entity.setGender(account.getGender());
            entity.setEmail(account.getEmail());
            entity.setPhone(account.getPhone());


        }else {
//        xử lý tạo mới
            // copy tu model sang entity
            BeanUtils.copyProperties(account, entity);
            Date date = new Date(System.currentTimeMillis());
            entity.setAcceptedDate(date);
            Role roleEntity = roleService.findRoleByRoleID(account.getRoleID());
            entity.setRole(roleEntity);
            entity.setStatus(1);
        }

//        Account entity = accountService.findAccountByEmail(account.getEmail());
//        if (entity != null){
//            ra.addFlashAttribute("MSG","Email is exist!!!");
//            return "redirect:/FYoGa/Login/ADMIN/User/CreateOrUpdate";
//        }


        // save
        accountService.save(entity);

        ra.addFlashAttribute("MSG","Save successfully!!!");

        //return "redirect:/FYoGa/Login/ADMIN/User/CreateOrUpdate?roleID="+entity.getRole().getRoleID();
        return "redirect:/FYoGa/Login/ADMIN/User?roleID="+account.getRoleID();
    }
}
