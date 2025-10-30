package com.example.Website_ban_hang.controller;

import com.example.Website_ban_hang.model.Order;
import com.example.Website_ban_hang.model.User;
import com.example.Website_ban_hang.service.CartService;
import com.example.Website_ban_hang.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user/gio-hang")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public String addToCart(@RequestParam("productId") int id,
                            @RequestParam("soLuong") int soLuong,
                            HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/guest";
        cartService.addToCart(user, id, soLuong);
        return "redirect:/user";
    }

    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/guest";

        model.addAttribute("cartItems", cartService.getCartByUser(user));
        model.addAttribute("totalPrice", cartService.getTotalPrice(user));

        model.addAttribute("customerName", user.getHoTen());
        model.addAttribute("customerPhone", user.getSdt());

        return "user/product/gio-hang";
    }

    @PostMapping("/update/{id}")
    public String updateQuantity(@PathVariable int id,
                                 @RequestParam("action") String action,
                                 HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/guest";

        cartService.updateQuantity(user, id, action);

        return "redirect:/user/gio-hang";
    }

    @PostMapping("/remove/{id}")
    public String removeItem(@PathVariable int id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null)
            cartService.removeItem(user, id);
        return "redirect:/user/gio-hang";
    }

    @PostMapping("/checkout")
    public String datHang(@RequestParam("sdt") String sdt,
                          @RequestParam("diaChi") String diaChi,
                          HttpSession session,
                          Model model) {

        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/guest/login";
        }

        double tongTien = cartService.getTotalPrice(user);

        Order donHang = orderService.datHang(user, sdt, diaChi, tongTien);

        cartService.clearCart(user);

        model.addAttribute("customerName", user.getHoTen());
        model.addAttribute("customerPhone", sdt);
        model.addAttribute("customerAddress", diaChi);
        model.addAttribute("orderId", donHang.getIdDonHang());
        model.addAttribute("totalPrice", tongTien);

        return "user/product/hoan-thanh-dat-hang";
    }

}
