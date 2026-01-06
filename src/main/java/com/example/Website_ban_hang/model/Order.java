package com.example.Website_ban_hang.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "donhang")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_don_hang")
    private int idDonHang;

    @ManyToOne
    @JoinColumn(name = "id_nguoi_dung")
    private User user;

    @Column(name = "thoi_gian_dat")
    private LocalDateTime thoiGianDat;

    @Column(name = "sdt_nguoi_nhan", length = 15)
    private String sdtNguoiNhan;

    @Column(name = "dia_chi", length = 255)
    private String diaChi;

    private double tongTien;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> chiTietDonHang;

    public Order() {}

    public Order(User user, String sdtNguoiNhan, String diaChi, double tongTien) {
        this.user = user;
        this.sdtNguoiNhan = sdtNguoiNhan;
        this.diaChi = diaChi;
        this.tongTien = tongTien;
        this.thoiGianDat = LocalDateTime.now();
    }

    public int getIdDonHang() { return idDonHang; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public LocalDateTime getThoiGianDat() { return thoiGianDat; }
    public void setThoiGianDat(LocalDateTime thoiGianDat) { this.thoiGianDat = thoiGianDat; }
    public String getSdtNguoiNhan() { return sdtNguoiNhan; }
    public void setSdtNguoiNhan(String sdtNguoiNhan) { this.sdtNguoiNhan = sdtNguoiNhan; }
    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }
    public double getTongTien() { return tongTien; }
    public void setTongTien(double tongTien) { this.tongTien = tongTien; }
    public List<OrderDetail> getChiTietDonHang() { return chiTietDonHang; }
    public void setChiTietDonHang(List<OrderDetail> chiTietDonHang) { this.chiTietDonHang = chiTietDonHang; }
}
