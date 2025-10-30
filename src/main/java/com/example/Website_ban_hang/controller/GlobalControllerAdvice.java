package com.example.Website_ban_hang.controller;

import com.example.Website_ban_hang.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("user")
    public User addUserToModel(HttpSession session) {
        return (User) session.getAttribute("user");
    }
}