package com.example.Website_ban_hang.model;

import jakarta.persistence.*;

@Entity
@Table(name = "chitietdonhang")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_chi_tiet")
    private int idChiTiet;

    @ManyToOne
    @JoinColumn(name = "id_don_hang")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "id_san_pham")
    private Product product;

    @Column(name = "so_luong")
    private int soLuong;

    public OrderDetail() {}

    public OrderDetail(Order order, Product product, int soLuong) {
        this.order = order;
        this.product = product;
        this.soLuong = soLuong;
    }

    // Getter và Setter
    public int getIdChiTiet() { return idChiTiet; }
    public Order getDonHang() { return order; }
    public void setDonHang(Order order) { this.order = order; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
}
