package com.example.Website_ban_hang.model;
import jakarta.persistence.*;

@Entity
@Table(name = "nguoidung")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nguoi_dung")
    private int id;

    @Column(name = "ten_dang_nhap", unique = true, nullable = false)
    private String tenDangNhap;

    @Column(name = "mat_khau", nullable = false)
    private String matKhau;

    @Column(name = "ho_ten")
    private String hoTen;

    @Column(name = "sdt")
    private String sdt;

    @Column(name = "vai_tro")
    private int vaiTro; // 0 = USER, 1 = ADMIN

    @Column(name = "email")
    private String email;

    public User(){}

    public User(int id, String tenDangNhap, String matKhau, String hoTen, String sdt, int vaiTro, String email) {
        this.id = id;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.vaiTro = vaiTro;
        this.email = email;
    }

    public int getIdNguoiDung() {
        return id;
    }

    public void setIdNguoiDung(int idNguoiDung) {
        this.id = idNguoiDung;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public int getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(int vaiTro) {
        this.vaiTro = vaiTro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
