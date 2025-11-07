package com.example.Website_ban_hang.controller;
import com.example.Website_ban_hang.model.User;
import com.example.Website_ban_hang.service.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        try {
            accountService.register(user);
            return "redirect:/guest?registerSuccess";
        } catch (RuntimeException e) {
            return "redirect:/guest?registerError";
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session) {
        User user = accountService.login(username, password);
        if (user == null) {
            return "redirect:/guest?loginError";
        }
        session.setAttribute("user", user);
        if(user.getVaiTro()==1)
            return "redirect:/admin";
        return "redirect:/user";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/guest";
    }
}
