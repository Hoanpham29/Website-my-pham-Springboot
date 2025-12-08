package com.example.Website_ban_hang.service.impl;

import com.example.Website_ban_hang.model.Contact;
import com.example.Website_ban_hang.model.Order;
import com.example.Website_ban_hang.model.Product;
import com.example.Website_ban_hang.model.User;
import com.example.Website_ban_hang.repository.ContactRepository;
import com.example.Website_ban_hang.repository.OrderRepository;
import com.example.Website_ban_hang.repository.ProductRepository;
import com.example.Website_ban_hang.repository.UserRepository;
import com.example.Website_ban_hang.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public long getTotalStock() {
        return orderRepository.countAllProductsInStock();
    }

    @Override
    public long getTotalUsers() {
        return userRepository.count();
    }

    @Override
    public double getTotalRevenue() {
        return orderRepository.sumTotalRevenue();
    }

    @Override
    public long getOrdersToday() {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(23, 59, 59);
        return orderRepository.countByDate(startOfDay, endOfDay);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public List<Product> getAllProducts(){return productRepository.findAll();}

}
