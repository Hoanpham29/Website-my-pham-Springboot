package com.example.Website_ban_hang.repository;

import com.example.Website_ban_hang.model.Cart;
import com.example.Website_ban_hang.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByUser(User user);
    Optional<Cart> findByUserAndProduct_Id(User user, int productId);
}
