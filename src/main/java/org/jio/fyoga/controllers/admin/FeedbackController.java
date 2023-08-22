package org.jio.fyoga.controllers.admin;

import org.jio.fyoga.entity.Class;
import org.jio.fyoga.entity.Feedback;
import org.jio.fyoga.model.FeedbackDTO;
import org.jio.fyoga.service.IFeedbackService;
import org.jio.fyoga.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

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


    @GetMapping("/submit")
    public String submitFeedback(@RequestParam("name") String name,
                                 @RequestParam("email") String email,
                                 @RequestParam("comment") String comment) {
        Feedback feedback = new Feedback();
        feedback.setName(name);
        feedback.setEmail(email);
        feedback.setComment(comment);
        feedback.setStatus(0); // Assuming 0 means "not processed"
        feedback.setDate(MyUtil.currentDate()); // Assuming you want to set the current date/time

        feedbackService.save(feedback);

        return "redirect:/"; // Redirect back to the home page with a success message
    }

    @GetMapping("/reStatus")
    public String restoreFeedback(@RequestParam int fbID) {
        Feedback feedback = feedbackService.findById(fbID);

        // Check if the Feedback entry exists
        if (feedback != null) {
            // Set the status to active (1)
            feedback.setStatus(1);
            feedbackService.save(feedback);
        }

        return "redirect:/FYoGa/Login/ADMIN/feedback";
    }
    @GetMapping("/remove")
    public String removeFeedback(@RequestParam int fbID) {
        Feedback feedback = feedbackService.findById(fbID);

        if (feedback != null) {
            feedback.setStatus(0);
            feedbackService.save(feedback);
        }

        return "redirect:/FYoGa/Login/ADMIN/feedback";
    }


}
