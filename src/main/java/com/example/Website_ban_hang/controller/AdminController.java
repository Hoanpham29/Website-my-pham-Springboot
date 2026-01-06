package com.example.Website_ban_hang.controller;

import com.example.Website_ban_hang.model.Order;
import com.example.Website_ban_hang.model.Product;
import com.example.Website_ban_hang.model.User;
import com.example.Website_ban_hang.service.AdminService;
import com.example.Website_ban_hang.service.ImageService;
import com.example.Website_ban_hang.service.ProductService;
import com.example.Website_ban_hang.service.UserService;
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
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    ProductService productService;

    @Autowired
    ImageService imageService;

    @Autowired
    UserService userService;

    public void SplitPageProducts(int page,
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
        SplitPageProducts(page,model,inputProduct);
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

    @PostMapping("/sanpham/sua/{id}")
    public String updateProduct(
            @PathVariable("id") int id,
            @RequestParam("ten") String ten,
            @RequestParam("phanloai") String phanLoai,
            @RequestParam("nhaCungCap") String ncc,
            @RequestParam("dungtich") int dungTich,
            @RequestParam("moTa") String moTa,
            @RequestParam("gia") double gia,
            @RequestParam("giamGia") int giamGia,
            @RequestParam("soLuongTonKho") int tonKho,
            @RequestParam("daban") int daBan,
            @RequestParam("ngaynhap") String ngayNhap,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile
    ) throws IOException {
        Product p = productService.getProductById(id);

        p.setTen(ten);
        p.setPhanloai(phanLoai);
        p.setNhaCungCap(ncc);
        p.setDungtich(dungTich);
        p.setMoTa(moTa);
        p.setGia(gia);
        p.setGiamGia(giamGia);
        p.setSoLuongTonKho(tonKho);
        p.setDaban(daBan);
        p.setNgaynhap(LocalDateTime.parse(ngayNhap));

        if (imageFile != null && !imageFile.isEmpty()) {
            String fileName = imageService.save(imageFile);
            p.setHinhAnh(fileName);
        }

        productService.save(p);

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


    public void SplitPageUsers(int page,
                          Model model,
                          List<User> inputUser)
    {
        int pageSize = 24;
        int totalProducts = inputUser.size();
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

        int start = page * pageSize;
        int end = Math.min(start + pageSize, totalProducts);

        List<User> users = inputUser.subList(start, end);

        model.addAttribute("users", users);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
    }

    @GetMapping("/nguoidung")
    public String showUserPage(@ModelAttribute("user") User user,
                                  @RequestParam(defaultValue = "0") int page,
                                  Model model,
                                  HttpSession session)
    {
        if(session.getAttribute("user")==null)
            return "redirect:/guest";

        List<User> inputUser = adminService.getAllUsers();
        SplitPageUsers(page,model,inputUser);

        if(user == null)
            return "redirect:/guest";

        return "admin/menu/users";
    }

    @PostMapping("/nguoidung/capnhat-vaitro")
    public String doUpdateRole(@RequestParam("id") Integer id,
                               @RequestParam("role") Integer role,
                               RedirectAttributes redirectAttributes)
    {
        User user = userService.findById(id);
        user.setVaiTro(role);
        userService.updateUser(user);

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Cập nhật vai trò thành công"
        );
        return "redirect:/admin/nguoidung";
    }

    public void SplitPageOrders(int page,
                               Model model,
                               List<Order> inputOrder)
    {
        int pageSize = 24;
        int totalOrders = inputOrder.size();
        int totalPages = (int) Math.ceil((double) totalOrders / pageSize);

        int start = page * pageSize;
        int end = Math.min(start + pageSize, totalOrders);

        List<Order> orders = inputOrder.subList(start, end);

        model.addAttribute("orders", orders);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
    }

    @GetMapping("/donhang")
    public String showOrdersList(@ModelAttribute("user") User user,
                                 @RequestParam(defaultValue = "0") int page,
                                 Model model,
                                 HttpSession session)
    {
        if(session.getAttribute("user")==null)
            return "redirect:/guest";

        List<Order> inputOrders = adminService.getAllOrders();
        SplitPageOrders(page,model,inputOrders);

        if(user == null)
            return "redirect:/guest";

        return "admin/menu/orders";
    }

}