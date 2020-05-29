package com.mcsumdu.hritsay.specialty.controllers;

import java.util.List;

import com.mcsumdu.hritsay.specialty.dao.*;
import com.mcsumdu.hritsay.specialty.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {
    @Autowired
    private NewsPostgresDAO newsPostgresDAO;
    @Autowired
    private EducatorsPostgresDAO educatorsPostgresDAO;
    @Autowired
    private StudentsPostgresDAO studentsPostgresDAO;
    @Autowired
    private EducationServicesPostgresDAO educationServicesPostgresDAO;
    @Autowired
    private GroupPostgresDAO groupPostgresDAO;
    @Autowired
    private EducDocumentsPostgresDAO educDocumentsPostgresDAO;

    @GetMapping("/")
    public String home(Model model) {
        Iterable<News> news = newsPostgresDAO.getThreeLastNews();
        model.addAttribute("news", news);
        return "home";
    }

    @GetMapping("/teachers")
    public String showAllTeachers(Model model) {
        List<Educator> educators = educatorsPostgresDAO.getAllEducators();
        model.addAttribute("teachers", educators);
        return "teachers";
    }

    @GetMapping("/students")
    public String showStudents(Model model) {
        List<Group> groups = groupPostgresDAO.getAllGroups();
        model.addAttribute("groups", groups);
        return "students";
    }

    @PostMapping("/students")
    public String showStudentsByGroup(@RequestParam String groupTitle, Model model) {
        List<Group> groups = groupPostgresDAO.getAllGroups();
        model.addAttribute("groups", groups);
        Group group = groupPostgresDAO.getGroupByTitle(groupTitle);
        List<Student> students = studentsPostgresDAO.getAllStudentsByGroup(group);
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/services")
    public String showAllServices(Model model) {
        List<Group> groups = groupPostgresDAO.getAllGroups();
        model.addAttribute("groups", groups);

        return "services";
    }

    @PostMapping("/services")
    public String showServicesByGroup(@RequestParam String groupTitle, Model model) {
        List<Group> groups = groupPostgresDAO.getAllGroups();
        model.addAttribute("groups", groups);

        int b = 1 + 1;
        int groupId = groupPostgresDAO.getGroupByTitle(groupTitle).getId();
        List<EducationService> services = educationServicesPostgresDAO.getServicesByGroupId(groupId);
        model.addAttribute("services", services);
        return "services";
    }

    @GetMapping("/methodicals")
    public String showAllMethodicals(Model model) {
        List<SiteDocument> documents = educDocumentsPostgresDAO.getAllMethodicalDocuments();
        model.addAttribute("documents", documents);
        return "methodicals";
    }

}
