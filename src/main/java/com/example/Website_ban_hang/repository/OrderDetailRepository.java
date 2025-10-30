package com.example.Website_ban_hang.repository;

import com.example.Website_ban_hang.model.OrderDetail;
import com.example.Website_ban_hang.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    List<OrderDetail> findByOrder(Order order);
}
