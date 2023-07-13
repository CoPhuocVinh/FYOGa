package org.jio.fyoga.controllers.admin;

import org.jio.fyoga.entity.Class;
import org.jio.fyoga.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/FYoGa/Login/ADMIN/class")
@Controller
public class ClassController {
    private final IClassService classService;

    @Autowired
    public ClassController(IClassService classService) {
        this.classService = classService;
    }

    @GetMapping("")
    public String getClass(Model model) {
        List<Class> classList = classService.findAll();
        model.addAttribute("CLASSLIST", classList);
        return "admin/page_list_class";
    }
}