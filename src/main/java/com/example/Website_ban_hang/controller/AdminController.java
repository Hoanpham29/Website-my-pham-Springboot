package com.example.Website_ban_hang.controller;

import com.example.Website_ban_hang.model.Product;
import com.example.Website_ban_hang.model.User;
import com.example.Website_ban_hang.service.AdminService;
import com.example.Website_ban_hang.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    ProductService productService;

    public void SplitPage(int page,
                          Model model,
                          List<Product> inputProduct)
    {
        int pageSize = 24;
        int totalProducts = inputProduct.size();
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

        int start = page * pageSize;
        int end = Math.min(start + pageSize, totalProducts);

        List<Product> products = inputProduct.subList(start, end);

        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
    }

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

    @GetMapping("/sanpham")
    public String showProductPage(@ModelAttribute("user") User user,
                               @RequestParam(defaultValue = "0") int page,
                               Model model,
                               HttpSession session)
    {
        if(session.getAttribute("user")==null)
            return "redirect:/guest";

        List<Product> inputProduct = adminService.getAllProducts();
        SplitPage(page,model,inputProduct);
        model.addAttribute("product", new Product());
        if(user == null)
            return "redirect:/guest";

        return "admin/menu/products";
    }


    @PostMapping("/sanpham/them")
    public String doAddProduct(@ModelAttribute Product products,
                               RedirectAttributes redirectAttributes,
                               @RequestParam("imageFile") MultipartFile file) throws IOException {

        if (!file.isEmpty()) {

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            Path uploadPath = Paths.get("uploads/" + fileName);

            Files.write(uploadPath, file.getBytes());

            products.setHinhAnh("uploads/" + fileName);
        } else {
            products.setHinhAnh("default.png");
        }
        productService.save(products);

        redirectAttributes.addFlashAttribute("successMessage", "Thêm sản phẩm thành công!");
        return "redirect:/admin/sanpham";
    }

    @PostMapping("/sanpham/xoa/{id}")
    public String doDeleteProduct(@PathVariable int id,
                                  RedirectAttributes redirectAttributes,
                                  Model model)
    {
        redirectAttributes.addFlashAttribute("successMessage", "Xóa sản phẩm thành công!");
        productService.deleteProductById(id);
        return "redirect:/admin/sanpham";
    }
}
