package com.example.Website_ban_hang.repository;

import com.example.Website_ban_hang.model.Order;
import com.example.Website_ban_hang.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUser(User user);

    @Query("SELECT SUM(p.soLuongTonKho) FROM Product p")
    long countAllProductsInStock();

    @Query("SELECT SUM(o.tongTien) FROM Order o")
    long sumTotalRevenue();

    @Query("SELECT COUNT(o) FROM Order o WHERE o.thoiGianDat BETWEEN :startOfDay AND :endOfDay")
    long countByDate(@Param("startOfDay") LocalDateTime startOfDay,
                     @Param("endOfDay") LocalDateTime endOfDay);

}