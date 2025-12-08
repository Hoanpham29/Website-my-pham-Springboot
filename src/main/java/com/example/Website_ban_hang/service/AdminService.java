package com.example.Website_ban_hang.service;
import com.example.Website_ban_hang.model.Contact;
import com.example.Website_ban_hang.model.Order;
import com.example.Website_ban_hang.model.Product;
import com.example.Website_ban_hang.model.User;
import java.util.List;

public interface AdminService {
    long getTotalStock();
    long getTotalUsers();
    double getTotalRevenue();
    long getOrdersToday();
    List<Order> getAllOrders();
    List<User> getAllUsers();
    List<Contact> getAllContacts();
    List<Product> getAllProducts();
}
