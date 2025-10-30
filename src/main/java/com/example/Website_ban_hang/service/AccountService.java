package com.example.Website_ban_hang.service;

import com.example.Website_ban_hang.model.User;
import com.example.Website_ban_hang.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public User register(User user)
    {
        if(accountRepository.findByTenDangNhap(user.getTenDangNhap())!=null)
            throw new RuntimeException("Tên đăng nhập đã tồn tại");
        user.setVaiTro(0);
        return accountRepository.save(user);
    }

    public User login(String username, String password)
    {
        User user = accountRepository.findByTenDangNhap(username);
        if(user != null && user.getMatKhau().equals(password))
            return user;
        return null;
    }
}
