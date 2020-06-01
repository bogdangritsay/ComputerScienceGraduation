package com.mcsumdu.hritsay.specialty.controllers.admin;

import com.mcsumdu.hritsay.specialty.dao.*;
import com.mcsumdu.hritsay.specialty.models.EducationService;
import com.mcsumdu.hritsay.specialty.models.Group;
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
public class EducationServicesAdminController {
    @Autowired
    private EducationServicesPostgresDAO educationServicesPostgresDAO;
    @Autowired
    private SubjectsPostgresDAO subjectsPostgresDAO;
    @Autowired
    private GroupPostgresDAO groupPostgresDAO;


    @GetMapping("/services-admin")
    public String showAllServices(Model model) {
        List<Group> groups = groupPostgresDAO.getAllGroups();
        model.addAttribute("groups", groups);
        return "admin_pages/services-admin";
    }

    @PostMapping("/services-admin")
    public String showServicesByGroup(@RequestParam String groupTitle, Model model) {
        List<Group> groups = groupPostgresDAO.getAllGroups();
        model.addAttribute("groups", groups);
        int groupId = groupPostgresDAO.getGroupByTitle(groupTitle).getId();
        List<EducationService> services = educationServicesPostgresDAO.getServicesByGroupId(groupId);
        model.addAttribute("services", services);
        return "admin_pages/services-admin";
    }

    @GetMapping("/services-admin/add")
    public String serviceAdd(Model model) {
        List<Group> groups = groupPostgresDAO.getAllGroups();
        List<Subject> subjects = subjectsPostgresDAO.getAllSubjects();
        model.addAttribute("groups", groups);
        model.addAttribute("subjects", subjects);
        return "admin_pages/services-add-admin";
    }


    @PostMapping("/services-admin/add")
    public String serviceAdd(@RequestParam String title,
                             @RequestParam String description,
                             @RequestParam int subject,
                             @RequestParam int group,
                             @RequestParam String url,
                             Model model) {

        educationServicesPostgresDAO.addEducService(title, description, subject, url, group);

        return "redirect:/services-admin";
    }

    @PostMapping("/services-admin/{id}/remove")
    public String newsDelete(@PathVariable(value = "id") int id, Model model) {
        educationServicesPostgresDAO.deleteEducService(id);
        return "redirect:/services-admin";
    }

}
