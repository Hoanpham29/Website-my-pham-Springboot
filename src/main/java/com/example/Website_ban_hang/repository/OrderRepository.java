package com.example.Website_ban_hang.repository;

import com.example.Website_ban_hang.model.Order;
import com.example.Website_ban_hang.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUser(User user);
}