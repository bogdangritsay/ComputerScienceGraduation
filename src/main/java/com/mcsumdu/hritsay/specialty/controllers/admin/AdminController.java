package com.mcsumdu.hritsay.specialty.controllers.admin;


import com.mcsumdu.hritsay.specialty.entity.User;
import com.mcsumdu.hritsay.specialty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;


    @GetMapping("/admin")
    public String home(Model model) {
        return "admin_pages/admin_home";
    }

    @PostMapping("/admin")
    public String  deleteUser(@RequestParam(required = true, defaultValue = "" ) Long userId,
                              @RequestParam(required = true, defaultValue = "" ) String action,
                              Model model) {
        if (action.equals("delete")){
            userService.deleteUser(userId);
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/users")
    public String usersAll(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "admin_pages/users-admin";
    }

    @GetMapping("/admin/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "admin_pages/registration";
    }

    @PostMapping("/admin/registration")
    public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "admin_pages/registration";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute("passwordError", "Паролі не співпадають");

            return "admin_pages/registration";
        }
        if (!userService.saveUser(userForm)){
            model.addAttribute("usernameError", "Користувач з таким логіном вже існує");
            return "admin_pages/registration";
        }

        return "redirect:/admin/users";
    }






}
