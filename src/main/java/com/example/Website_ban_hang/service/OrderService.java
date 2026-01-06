package com.example.Website_ban_hang.service;

import com.example.Website_ban_hang.model.*;
import com.example.Website_ban_hang.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class    OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private CartRepository cartRepository;

    public Order datHang(User user, String sdt, String diaChi, double tongTien) {
        Order order = new Order(user, sdt, diaChi, tongTien);
        order = orderRepository.save(order);
        List<Cart> carts = cartRepository.findByUser(user);

        List<OrderDetail> chiTietList = new ArrayList<>();
        for (Cart cart : carts) {
            OrderDetail chiTiet = new OrderDetail(order, cart.getProduct(), cart.getSoLuong());
            chiTietList.add(chiTiet);
        }

        orderDetailRepository.saveAll(chiTietList);
        cartRepository.deleteAll(carts);

        return order;
    }

    public List<Order> getOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }
}
