package com.mcsumdu.hritsay.specialty.controllers.admin;

import com.mcsumdu.hritsay.specialty.dao.SubjectsPostgresDAO;
import com.mcsumdu.hritsay.specialty.models.Role;
import com.mcsumdu.hritsay.specialty.models.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SubjectsAdminController {
    @Autowired
    private SubjectsPostgresDAO subjectsPostgresDAO;

    @GetMapping("/subjects-admin")
    public String subjectsAdminMain(Model model) {
        List<Subject> subjects = subjectsPostgresDAO.getAllSubjects();
        model.addAttribute("subjects", subjects);
        return "admin_pages/subjects-admin";
    }


    @GetMapping("/subjects-admin/add")
    public String subjectsAdd() {
        return "admin_pages/subjects-admin-add";
    }

    @PostMapping("/subjects-admin/add")
    public String subjectsAdd(
            @RequestParam String title,
            Model model) {
        subjectsPostgresDAO.addSubject(title);
        return "redirect:/subjects-admin";
    }


    @PostMapping("/subjects-admin/{id}/remove")
    public String subjectsDelete(@PathVariable(value = "id") int id, Model model) {
        subjectsPostgresDAO.deleteSubject(id);
        return "redirect:/subjects-admin";
    }
}
