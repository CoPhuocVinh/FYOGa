package org.jio.fyoga.controllers.admin;/*  Welcome to Jio word
    @author: Jio
    Date: 7/12/2023
    Time: 4:04 PM
    
    ProjectName: FYoGa
    Jio: I wish you always happy with coding <3
*/

import org.jio.fyoga.entity.Account;
import org.jio.fyoga.model.AccountDTO;
import org.jio.fyoga.service.IAccountService;
import org.jio.fyoga.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/CreateOrUpdate")
    public String UserOrUpdate(@RequestParam int roleID,
                               @RequestParam int isEdit,
                               @RequestParam(required = false) int AccountID,
                               Model model) {

        //xu ly CREATE
        AccountDTO account = new AccountDTO();
        account.setIsEdit(false);
        model.addAttribute("ACCOUNT", account);

        return "admin/createnewTeacher";
    }
}
