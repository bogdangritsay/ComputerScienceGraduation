package com.mcsumdu.hritsay.specialty.controllers.admin;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String userList(Model model) {
        return "admin_pages/admin_home";
    }

}
