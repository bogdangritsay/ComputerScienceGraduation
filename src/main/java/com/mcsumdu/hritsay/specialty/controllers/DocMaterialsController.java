package com.mcsumdu.hritsay.specialty.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/educmaterials")
public class DocMaterialsController {

    @GetMapping("/allDocs")
    public String showAllDocuments() {
        return "documents";
    }


}
