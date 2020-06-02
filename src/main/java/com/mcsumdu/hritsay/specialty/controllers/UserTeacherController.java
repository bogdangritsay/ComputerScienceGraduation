package com.mcsumdu.hritsay.specialty.controllers;

import com.mcsumdu.hritsay.specialty.dao.EducDocumentsPostgresDAO;
import com.mcsumdu.hritsay.specialty.dao.EducationServicesPostgresDAO;
import com.mcsumdu.hritsay.specialty.dao.GroupPostgresDAO;
import com.mcsumdu.hritsay.specialty.dao.SubjectsPostgresDAO;
import com.mcsumdu.hritsay.specialty.models.EducationService;
import com.mcsumdu.hritsay.specialty.models.Group;
import com.mcsumdu.hritsay.specialty.models.SiteDocument;
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
public class UserTeacherController {
    @Autowired
    private EducDocumentsPostgresDAO educDocumentsPostgresDAO;
    @Autowired
    private EducationServicesPostgresDAO educationServicesPostgresDAO;
    @Autowired
    private SubjectsPostgresDAO subjectsPostgresDAO;
    @Autowired
    private GroupPostgresDAO groupPostgresDAO;



    @GetMapping("/teacher-panel")
    public String teacherPanel() {
        return "teacher-panel";
    }


    @GetMapping("/teacher-panel/methodicals")
    public String methodicalsMain(Model model) {
        List<SiteDocument> documents = educDocumentsPostgresDAO.getAllMethodicalDocuments();
        model.addAttribute("documents", documents);
        return "teacher_pages/methodicals-teacher";
    }

    @GetMapping("/teacher-panel/methodicals/add")
    public String methodicalsAdd() {
        return "teacher_pages/teacher-methodicals-add";
    }

    @PostMapping("/teacher-panel/methodicals/add")
    public String methodicalsAdd(
            @RequestParam String title,
            @RequestParam String url,
            @RequestParam String description,
            Model model) {
        educDocumentsPostgresDAO.addDocument(title, description, url);
        return "redirect:/teacher-panel/methodicals";
    }


    @PostMapping("/teacher-panel/methodicals/{id}/remove")
    public String methodicalsDelete(@PathVariable(value = "id") int id, Model model) {
        educDocumentsPostgresDAO.removeDocument(id);
        return "redirect:/teacher-panel/methodicals";
    }


    @GetMapping("/teacher-panel/services")
    public String showAllServices(Model model) {
        List<Group> groups = groupPostgresDAO.getAllGroups();
        model.addAttribute("groups", groups);
        return "teacher_pages/services-teacher";
    }

    @PostMapping("/teacher-panel/services")
    public String showServicesByGroup(@RequestParam String groupTitle, Model model) {
        List<Group> groups = groupPostgresDAO.getAllGroups();
        model.addAttribute("groups", groups);
        int groupId = groupPostgresDAO.getGroupByTitle(groupTitle).getId();
        List<EducationService> services = educationServicesPostgresDAO.getServicesByGroupId(groupId);
        model.addAttribute("services", services);
        return "teacher_pages/services-teacher";
    }

    @GetMapping("/teacher-panel/services/add")
    public String serviceAdd(Model model) {
        List<Group> groups = groupPostgresDAO.getAllGroups();
        List<Subject> subjects = subjectsPostgresDAO.getAllSubjects();
        model.addAttribute("groups", groups);
        model.addAttribute("subjects", subjects);
        return "teacher_pages/teacher-services-add";
    }


    @PostMapping("/teacher-panel/services/add")
    public String serviceAdd(@RequestParam String title,
                             @RequestParam String description,
                             @RequestParam int subject,
                             @RequestParam int group,
                             @RequestParam String url,
                             Model model) {

        educationServicesPostgresDAO.addEducService(title, description, subject, url, group);

        return "redirect:/teacher-panel/services";
    }

    @PostMapping("/teacher-panel/services/{id}/remove")
    public String newsDelete(@PathVariable(value = "id") int id, Model model) {
        educationServicesPostgresDAO.deleteEducService(id);
        return "redirect:/teacher-panel/services";
    }


}
