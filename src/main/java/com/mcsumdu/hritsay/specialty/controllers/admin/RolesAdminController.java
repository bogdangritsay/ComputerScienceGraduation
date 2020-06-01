package com.mcsumdu.hritsay.specialty.controllers.admin;


import com.mcsumdu.hritsay.specialty.dao.RolesPostgresDAO;
import com.mcsumdu.hritsay.specialty.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RolesAdminController {
    @Autowired
    private RolesPostgresDAO rolesPostgresDAO;

    @GetMapping("/roles-admin")
    public String rolesAdminMain(Model model) {
        List<Role> roles = rolesPostgresDAO.getAllRoles();
        model.addAttribute("roles", roles);
        return "admin_pages/roles-admin";
    }


    @GetMapping("/roles-admin/add")
    public String rolesAdd() {
        return "admin_pages/roles-admin-add";
    }

    @PostMapping("/roles-admin/add")
    public String rolesAdd(
            @RequestParam String title,
            @RequestParam int rank,
            Model model) {
        rolesPostgresDAO.addRole(title, rank);
        return "redirect:/roles-admin";
    }


    @PostMapping("/roles-admin/{id}/remove")
    public String rolesDelete(@PathVariable(value = "id") int id, Model model) {
        rolesPostgresDAO.deleteRole(id);
        return "redirect:/roles-admin";
    }
}
