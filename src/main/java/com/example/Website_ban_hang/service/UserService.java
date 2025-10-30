package com.example.Website_ban_hang.service;

import com.example.Website_ban_hang.model.User;
import com.example.Website_ban_hang.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void updateUser(User user) {
        userRepository.save(user);
    }
}
