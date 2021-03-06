package com.mcsumdu.hritsay.specialty.controllers.admin;


import com.mcsumdu.hritsay.specialty.dao.*;
import com.mcsumdu.hritsay.specialty.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TeachersAdminController {
    @Autowired
    private EducatorsPostgresDAO educatorsPostgresDAO;
    @Autowired
    private  UrlsPostgresDAO urlsPostgresDAO;
    @Autowired
    private SubjectsPostgresDAO subjectsPostgresDAO;
    @Autowired
    private RolesPostgresDAO rolesPostgresDAO;

    @GetMapping("/educators-admin")
    public String educatorsAdminMain(Model model) {
        List<Educator> educators = educatorsPostgresDAO.getAllEducators();
        model.addAttribute("teachers", educators);
        return "admin_pages/educators-admin";
    }


    @GetMapping("/educators-admin/add")
    public String educatorAdd(Model model) {
        List<Role> roles = rolesPostgresDAO.getAllRoles();
        List<Educator> educators = educatorsPostgresDAO.getAllEducators();
        model.addAttribute("roles", roles);
        model.addAttribute("educators", educators);
        List<Subject> subjects = subjectsPostgresDAO.getAllSubjects();
        model.addAttribute("subjects", subjects);
        return "admin_pages/educators-add-admin";
    }

    @PostMapping("/educators-admin/add")
    public String educatorAddNew(
                                @RequestParam String name,
                               @RequestParam String surname,
                               @RequestParam String patronymic,
                               @RequestParam int role,
                               @RequestParam String description,
                               @RequestParam String imgUrl,
                               @RequestParam int manager,
                               @RequestParam List<Integer> subjectList,
                               Model model) {
       urlsPostgresDAO.addNewUrl(imgUrl, "img");
       int urlId = urlsPostgresDAO.getUrlIdByString(imgUrl);
       UrlAddress urlAddress = urlsPostgresDAO.getUrlById(urlId);
       Role roleObj = rolesPostgresDAO.getRoleById(role);
       Educator managerObj = educatorsPostgresDAO.getEducatorById(manager);

       Educator educator = new Educator(name, surname, patronymic, description, urlAddress, managerObj, roleObj);

        educatorsPostgresDAO.addEducator(name, surname, patronymic, description, urlId, managerObj.getEducatorId(), roleObj.getRoleId(), subjectList);

        System.out.println(name + "\t" + surname + "\t" + patronymic + "\t" + role + "\t" + description + "\t" + imgUrl + "\t mgr_id: " + manager);

        return "redirect:/educators-admin";
    }



    @PostMapping("/educators-admin/{id}/remove")
    public String educatorDelete(@PathVariable(value = "id") int id, Model model) {
        educatorsPostgresDAO.deleteEducator(id);
        return "redirect:/educators-admin";
    }




}
