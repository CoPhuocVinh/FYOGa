package org.jio.fyoga.controllers.web;/*  Welcome to Jio word
    @author: Jio
    Date: 6/19/2023
    Time: 12:44 AM
    
    ProjectName: demoSpring01
    Jio: I wish you always happy with coding <3
*/

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.jio.fyoga.entity.Account;
import org.jio.fyoga.entity.Register;
import org.jio.fyoga.entity.Role;
import org.jio.fyoga.model.AccountDTO;
import org.jio.fyoga.service.IAccountService;
import org.jio.fyoga.service.IRegisterService;
import org.jio.fyoga.service.IRoleService;
import org.jio.fyoga.util.MyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/FYoGa/Login/User")
@Controller
public class UserController {

    @Autowired
    IAccountService accountService;

    @Autowired
    IRoleService roleService;

    @Autowired
    IRegisterService registerService;


    public String GetStaff(Model model) {
        List<Account> accounts = accountService.findAccountByRole(3);
        model.addAttribute("LISTSTAFF", accounts);
        return "web/";
    }

    @GetMapping("/HistoryPay")
    public String Ristorypage(HttpSession session, Model model) {
        if (MyUtil.checkAuthen(session)) {
            Account account = (Account) session.getAttribute("USER");

        // xủ lý code trong đây
            // chưa thanh toán
            List<Register> registers_paying = new ArrayList<>();
            registers_paying = registerService.findByCustomer_AccountIDAndStatus(account.getAccountID(),2);
            model.addAttribute(("REGISTER_PAYING"), registers_paying);

            // thanh toan xong
            List<Register> registers_payed = new ArrayList<>();
            registers_paying = registerService.findByCustomer_AccountIDAndStatus(account.getAccountID(),1);
            model.addAttribute(("REGISTER_PAYED"), registers_payed);
            return "web/historyPay";
        }
        return "web/login";
    }

    @GetMapping("/EditInfor")
    public String Editpage(HttpSession session, Model model) {
        if (MyUtil.checkAuthen(session)) {
        // xủ lý code trong đây
            Account accountEntity = (Account) session.getAttribute("USER");
            AccountDTO accountDTO = new AccountDTO();
            BeanUtils.copyProperties(accountEntity, accountDTO);
            accountDTO.setGender(accountDTO.getGender().trim());
            accountDTO.setPhone(accountDTO.getPhone().trim());
            model.addAttribute("USER", accountDTO);
            return "web/user-editInfo";
        }
        return "web/login";
    }

    @PostMapping("/EditInfor")
    public String EditUser(HttpSession session, RedirectAttributes ra,@ModelAttribute AccountDTO accountDTO) {
        if (MyUtil.checkAuthen(session)) {
            // xủ lý code trong đây
            Account accountEntity = (Account) session.getAttribute("USER");
            accountEntity.setGender(accountDTO.getGender());
            accountEntity.setPhone(accountDTO.getPhone());
            accountEntity.setFullName(accountDTO.getFullName());

            accountService.save(accountEntity);
            ra.addFlashAttribute("MSG", "The user has been update successfully.");

            return "redirect:/FYoGa/Login/User/EditInfor";
        }
        return "web/login";
    }


    @GetMapping("/ChangePass")
    public String ChangePass(HttpSession session,Model model) {
        if (MyUtil.checkAuthen(session)) {
        // xủ lý code trong đây
            Account accountEntity = (Account) session.getAttribute("USER");
            AccountDTO accountDTO = new AccountDTO();
            BeanUtils.copyProperties(accountEntity, accountDTO);
            model.addAttribute("USER", accountDTO);
            return "web/user-editPass";
        }
        return "web/login";
    }

    @PostMapping("/ChangePass")
    public String ChangePassUser(HttpSession session, RedirectAttributes ra,@ModelAttribute AccountDTO accountDTO) {
        if (MyUtil.checkAuthen(session)) {
            // xủ lý code trong đây
            Account accountEntity = (Account) session.getAttribute("USER");
            if(accountEntity.getPassword().equals(accountDTO.getPassword())){
                accountEntity.setPassword(accountDTO.getNewPassword());
                accountService.save(accountEntity);
                ra.addFlashAttribute("MSG", "The password update successfully !!!.");
            }else {
                ra.addFlashAttribute("MSG", "The password incorrect !!!.");

            }



            return "redirect:/FYoGa/Login/User/ChangePass";
        }
        return "web/login";
    }
    ///FYoGa/Login/User/ScheduleClass
    @GetMapping("/ScheduleClass")
    public String ScheduleClass(HttpSession session,Model model) {
        if (MyUtil.checkAuthen(session)) {
            // xủ lý code trong đây

            return "web/scheduleDetail";
        }
        return "web/login";
    }
}

