package com.mcsumdu.hritsay.specialty.controllers.admin;


import com.mcsumdu.hritsay.specialty.dao.EducatorsPostgresDAO;
import com.mcsumdu.hritsay.specialty.dao.NewsPostgresDAO;
import com.mcsumdu.hritsay.specialty.dao.UrlsPostgresDAO;
import com.mcsumdu.hritsay.specialty.models.Educator;
import com.mcsumdu.hritsay.specialty.models.News;
import com.mcsumdu.hritsay.specialty.models.Role;
import com.mcsumdu.hritsay.specialty.models.UrlAddress;
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


    @GetMapping("/educators-admin")
    public String educatorsAdminMain(Model model) {
        List<Educator> educators = educatorsPostgresDAO.getAllEducators();
        model.addAttribute("teachers", educators);
        return "admin_pages/educators-admin";
    }


    @GetMapping("/educators-admin/add")
    public String educatorAdd(Model model) {
        List<Role> roles = educatorsPostgresDAO.getAllRoles();
        List<Educator> educators = educatorsPostgresDAO.getAllEducators();
        model.addAttribute("roles", roles);
        model.addAttribute("educators", educators);
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
                               Model model) {

       urlsPostgresDAO.addNewUrl(imgUrl, "img");
       int urlId = urlsPostgresDAO.getUrlIdByString(imgUrl);
       UrlAddress urlAddress = urlsPostgresDAO.getUrlById(urlId);
       Role roleObj = educatorsPostgresDAO.getRoleById(role);
       Educator managerObj = educatorsPostgresDAO.getEducatorById(manager);

       Educator educator = new Educator(name, surname, patronymic, description, urlAddress, managerObj, roleObj);

        educatorsPostgresDAO.addEducator(educator);

        System.out.println(name + "\t" + surname + "\t" + patronymic + "\t" + role + "\t" + description + "\t" + imgUrl + "\t mgr_id: " + manager);

        return "redirect:/educators-admin";
    }



    @PostMapping("/educators-admin/{id}/remove")
    public String educatorDelete(@PathVariable(value = "id") int id, Model model) {
        educatorsPostgresDAO.deleteEducator(id);
        return "redirect:/educators-admin";
    }




}
