package com.example.Website_ban_hang.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "giohang")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gio_hang")
    private int idGioHang;

    @ManyToOne
    @JoinColumn(name = "id_nguoi_dung", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Product product;

    @Column(name = "so_luong")
    private int soLuong;

    @Column(name = "ngay_tao")
    private LocalDateTime ngayTao;

    public Cart() {
        this.ngayTao = LocalDateTime.now();
    }

    public Cart(User user, Product product, int soLuong) {
        this.user = user;
        this.product = product;
        this.soLuong = soLuong;
        this.ngayTao = LocalDateTime.now();
    }

    // --- Getter và Setter ---
    public int getIdGioHang() {
        return idGioHang;
    }

    public void setIdGioHang(int idGioHang) {
        this.idGioHang = idGioHang;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public LocalDateTime getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDateTime ngayTao) {
        this.ngayTao = ngayTao;
    }

    public double getTotalPrice() {
        if (product != null) {
            double price = product.getGia();
            int giamGia = product.getGiamGia();
            return price * soLuong * (1 - giamGia / 100.0);
        }
        return 0;
    }
}
