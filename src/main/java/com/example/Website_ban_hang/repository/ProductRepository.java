package com.example.Website_ban_hang.repository;
import com.example.Website_ban_hang.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByNgaynhapAfterOrderByNgaynhapDesc(LocalDateTime sixMonthAgo);

    @Query("SELECT product from Product product WHERE product.giamGia > 0")
    List<Product> findDiscountProducts();

    @Query("SELECT product from Product product WHERE product.daban > 50")
    List<Product> findTopseller();

    List<Product> findByPhanloai(String phanloai);

    @Query("SELECT product from Product product WHERE LOWER (product.ten) LIKE LOWER (CONCAT('%',: query, '%'))"
            + "OR LOWER(product.phanloai) LIKE LOWER (CONCAT('%', :query, '%'))")
    List<Product> searchProducts (String query);

    @Modifying
    @Transactional
    @Query("DELETE FROM Product product WHERE product.id = :id")
    void deleteById(@Param("id") Integer id);
}
