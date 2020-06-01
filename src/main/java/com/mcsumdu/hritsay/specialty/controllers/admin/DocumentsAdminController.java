package com.mcsumdu.hritsay.specialty.controllers.admin;

import com.mcsumdu.hritsay.specialty.dao.EducDocumentsPostgresDAO;
import com.mcsumdu.hritsay.specialty.dao.EducatorsPostgresDAO;
import com.mcsumdu.hritsay.specialty.dao.SubjectsPostgresDAO;
import com.mcsumdu.hritsay.specialty.dao.UrlsPostgresDAO;
import com.mcsumdu.hritsay.specialty.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DocumentsAdminController {
    @Autowired
    private EducDocumentsPostgresDAO educDocumentsPostgresDAO;


    @GetMapping("/methodicals-admin")
    public String methodicalsAdminMain(Model model) {
        List<SiteDocument> documents = educDocumentsPostgresDAO.getAllMethodicalDocuments();
        model.addAttribute("documents", documents);
        return "admin_pages/methodicals-admin";
    }

    @GetMapping("/methodicals-admin/add")
    public String methodicalsAdd() {
        return "admin_pages/methodicals-admin-add";
    }

    @PostMapping("/methodicals-admin/add")
    public String methodicalsAdd(
            @RequestParam String title,
            @RequestParam String url,
            @RequestParam String description,
            Model model) {
        educDocumentsPostgresDAO.addDocument(title, description, url);
        return "redirect:/methodicals-admin";
    }


    @PostMapping("/methodicals-admin/{id}/remove")
    public String methodicalsDelete(@PathVariable(value = "id") int id, Model model) {
        educDocumentsPostgresDAO.removeDocument(id);
        return "redirect:/methodicals-admin";
    }

}





