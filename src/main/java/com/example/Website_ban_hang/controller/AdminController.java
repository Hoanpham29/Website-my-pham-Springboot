package com.example.Website_ban_hang.controller;

import com.example.Website_ban_hang.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping
    public String showAdminPage(Model model)
    {
        model.addAttribute("total_stock", adminService.getTotalStock());
        model.addAttribute("total_users", adminService.getTotalUsers());
        model.addAttribute("total_revenue", adminService.getTotalRevenue());
        model.addAttribute("total_orders_today", adminService.getOrdersToday());
        model.addAttribute("orders", adminService.getAllOrders());
        model.addAttribute("users", adminService.getAllUsers());
        model.addAttribute("contacts", adminService.getAllContacts());

        return "admin/dashboard";
    }
}
