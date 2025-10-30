package com.example.Website_ban_hang.service;

import com.example.Website_ban_hang.model.Cart;
import com.example.Website_ban_hang.model.Order;
import com.example.Website_ban_hang.model.Product;
import com.example.Website_ban_hang.model.User;
import com.example.Website_ban_hang.repository.CartRepository;
import com.example.Website_ban_hang.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Cart> getCartByUser(User user) {
        return cartRepository.findByUser(user);
    }

    public void addToCart(User user, int productId, int soLuong) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) return;

        Cart item = cartRepository.findByUserAndProduct_Id(user, productId).orElse(null);
        if (item != null) {
            item.setSoLuong(item.getSoLuong() + soLuong);
        } else {
            item = new Cart(user, product, soLuong);
        }
        cartRepository.save(item);
    }

    public void removeItem(User user, int productId) {
        cartRepository.findByUserAndProduct_Id(user, productId)
                .ifPresent(cartRepository::delete);
    }

    public void clearCart(User user) {
        List<Cart> items = cartRepository.findByUser(user);
        cartRepository.deleteAll(items);
    }

    public double getTotalPrice(User user) {
        return cartRepository.findByUser(user).stream()
                .mapToDouble(item -> {
                    double gia = item.getProduct().getGia() ;
                    int giamgia = item.getProduct().getGiamGia();

                    if(giamgia > 0 )
                        gia = gia * (100-giamgia)/100.0;
                    return gia * item.getSoLuong();
                })
                .sum();
    }

    public void updateQuantity(User user, int productId, String action) {
        Optional<Cart> optionalCart = cartRepository.findByUserAndProduct_Id(user, productId);

        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();

            if ("increase".equals(action)) {
                cart.setSoLuong(cart.getSoLuong() + 1);
            } else if ("decrease".equals(action) && cart.getSoLuong() > 1) {
                cart.setSoLuong(cart.getSoLuong() - 1);
            }

            cartRepository.save(cart);
        }
    }

}
