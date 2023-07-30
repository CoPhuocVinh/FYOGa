package org.jio.fyoga.controllers.admin;

import org.jio.fyoga.entity.Class;
import org.jio.fyoga.entity.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/FYoGa/Login/ADMIN/post")
@Controller
public class PostController {

    @GetMapping("")
    public String getClass(Model model) {


        return "admin/page_list_blog";
    }

}
