package com.mcsumdu.hritsay.specialty.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServicesController {

    @GetMapping("/remote_services")
    public String showAllServices() {
        return "remote_services";
    }
}
