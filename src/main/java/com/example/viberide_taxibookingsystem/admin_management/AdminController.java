package com.example.viberide_taxibookingsystem.admin_management;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class AdminController {

    @GetMapping("/admins")
    public String viewAllAdmins(Model model) throws IOException {
        List<Admin> adminList = AdminFileHandler.getAllAdmins();
        model.addAttribute("adminList", adminList);
        return "admin-list";
    }

    @GetMapping("/admins/new")
    public String showAdminForm() { return "admin-form"; }

    @PostMapping("/admins/super")
    public String createSuperAdmin(
            @RequestParam String adminId, @RequestParam String name,
            @RequestParam String email, @RequestParam String password,
            @RequestParam String department, @RequestParam String accessLevel,
            @RequestParam int managedAdmins) throws IOException {
        SuperAdmin sa = new SuperAdmin(adminId, name, email, password, department,
                                       LocalDate.now(), accessLevel, managedAdmins);
        AdminFileHandler.addAdmin(sa);
        return "redirect:/admins";
    }

    @PostMapping("/admins/support")
    public String createSupportAdmin(
            @RequestParam String adminId, @RequestParam String name,
            @RequestParam String email, @RequestParam String password,
            @RequestParam String department, @RequestParam String ticketCategory,
            @RequestParam int resolvedTickets) throws IOException {
        SupportAdmin spa = new SupportAdmin(adminId, name, email, password, department,
                                            LocalDate.now(), ticketCategory, resolvedTickets);
        AdminFileHandler.addAdmin(spa);
        return "redirect:/admins";
    }

    @GetMapping("/admins/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) throws IOException {
        Admin admin = AdminFileHandler.getAdminById(id);
        if (admin instanceof SuperAdmin) {
            model.addAttribute("type", "SUPER");
            model.addAttribute("superAdmin", (SuperAdmin) admin);
        } else if (admin instanceof SupportAdmin) {
            model.addAttribute("type", "SUPPORT");
            model.addAttribute("supportAdmin", (SupportAdmin) admin);
        } else {
            return "redirect:/admins";
        }
        return "admin-edit";
    }

    @PostMapping("/admins/update/super")
    public String updateSuper(
            @RequestParam String adminId, @RequestParam String name,
            @RequestParam String email, @RequestParam String password,
            @RequestParam String department, @RequestParam String date,
            @RequestParam String accessLevel, @RequestParam int managedAdmins) throws IOException {
        SuperAdmin sa = new SuperAdmin(adminId, name, email, password, department,
                                       LocalDate.parse(date), accessLevel, managedAdmins);
        AdminFileHandler.updateAdmin(sa);
        return "redirect:/admins";
    }

    @PostMapping("/admins/update/support")
    public String updateSupport(
            @RequestParam String adminId, @RequestParam String name,
            @RequestParam String email, @RequestParam String password,
            @RequestParam String department, @RequestParam String date,
            @RequestParam String ticketCategory, @RequestParam int resolvedTickets) throws IOException {
        SupportAdmin spa = new SupportAdmin(adminId, name, email, password, department,
                                            LocalDate.parse(date), ticketCategory, resolvedTickets);
        AdminFileHandler.updateAdmin(spa);
        return "redirect:/admins";
    }

    @GetMapping("/admins/delete/{id}")
    public String deleteAdmin(@PathVariable String id) throws IOException {
        AdminFileHandler.deleteAdmin(id);
        return "redirect:/admins";
    }
}
