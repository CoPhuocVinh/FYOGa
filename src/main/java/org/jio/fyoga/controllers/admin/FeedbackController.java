package org.jio.fyoga.controllers.admin;


import jakarta.servlet.http.HttpSession;
import org.jio.fyoga.entity.Account;
import org.jio.fyoga.entity.Class;
import org.jio.fyoga.entity.Course;
import org.jio.fyoga.entity.Feedback;
import org.jio.fyoga.model.AccountDTO;
import org.jio.fyoga.model.ClassDTO;
import org.jio.fyoga.service.IAccountService;
import org.jio.fyoga.service.IClassService;
import org.jio.fyoga.service.ICourseService;
import org.jio.fyoga.service.IFeedbackService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

@RequestMapping("/FYoGa/Login/ADMIN/feedback")
@Controller
public class FeedbackController {
    @Autowired
    private IFeedbackService feedbackService;

    @GetMapping("")
    public String getFeedback(Model model) {
        List<Feedback> fbListON = feedbackService.findByStatus(1);
        model.addAttribute("FB_ON", fbListON);

        List<Feedback> fbListOff = feedbackService.findByStatus(0);
        model.addAttribute("FB_OFF", fbListOff);

        return "admin/page_feedback";
    }
}
