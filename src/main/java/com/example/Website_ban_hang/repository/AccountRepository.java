package com.example.Website_ban_hang.repository;

import com.example.Website_ban_hang.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<User, Integer> {

    User findByTenDangNhap(String tenDangNhap);

    boolean existsByTenDangNhap(String tenDangNhap);
}
