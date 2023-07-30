package org.jio.fyoga.controllers.admin;

import org.jio.fyoga.entity.Class;
import org.jio.fyoga.entity.Feedback;
import org.jio.fyoga.model.FeedbackDTO;
import org.jio.fyoga.service.IFeedbackService;
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
        try {
            List<Feedback> fbListON = feedbackService.findByStatus(1);
            model.addAttribute("FB_ON", fbListON);

            List<Feedback> fbListOff = feedbackService.findByStatus(0);
            model.addAttribute("FB_OFF", fbListOff);

            return "admin/page_feedback";
        } catch (Exception e) {
            // Log the exception or print the error message
            e.printStackTrace();
            // You can also add a custom error message to display on the UI
            model.addAttribute("errorMessage", "An error occurred while fetching feedback data.");
            return "common/error/404";
        }
    }

    @GetMapping("/form")
    public String showFeedbackForm() {
        return "feedback_form"; // Return the name of the HTML template for the feedback form
    }
    @PostMapping("/submit")
    public String submitFeedback(@ModelAttribute FeedbackDTO feedbackDTO) {
        // Convert the FeedbackDTO to Feedback entity
        Feedback feedback = new Feedback();
        feedback.setName(feedbackDTO.getName());
        feedback.setEmail(feedbackDTO.getEmail());
        feedback.setComment(feedbackDTO.getComment());
        feedback.setStatus(0); // Set the status to 0 (assuming 0 means "not processed")
        feedback.setDate(new Date()); // Set the current date/time

        // Save the feedback to the database
        feedbackService.save(feedback);

        return "redirect:/?success"; // Redirect back to the home page with a success message
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

}
