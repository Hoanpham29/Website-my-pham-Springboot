package com.example.Website_ban_hang.service;
import com.example.Website_ban_hang.model.Product;
import com.example.Website_ban_hang.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    LocalDateTime sixMonthAgo = LocalDateTime.now().minusMonths(6);
    public List<Product> getNewProduct(){return  productRepository.findByNgaynhapAfterOrderByNgaynhapDesc(sixMonthAgo);}

    public List<Product> getDiscountProduct(){return  productRepository.findDiscountProducts();}

    public List<Product> getTopSeller(){return productRepository.findTopseller(); }

    public List<Product> getProductByCategory(String phanloai) {return productRepository.findByPhanloai(phanloai);}

    public List<Product> getProductBySearch(String query){return productRepository.searchProducts(query);}

    @Transactional
    public void deleteProductById(int id){productRepository.deleteById(id);}

    public void save(Product product)
    {
        productRepository.save(product);
    }

}
