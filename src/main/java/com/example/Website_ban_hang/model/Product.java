package com.example.Website_ban_hang.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sanpham")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ten")
    private String ten;

    @Column(name = "phan_loai")
    private String phanloai;

    @Column(name = "nha_cung_cap")
    private String nhaCungCap;

    @Column(name = "dung_tich")
    private int dungtich;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "gia")
    private double gia;

    @Column(name = "giam_gia")
    private int giamGia;

    @Column(name = "so_luong_ton_kho")
    private int soLuongTonKho;

    @Column(name = "da_ban")
    private int daban;

    @Column(name = "ngay_nhap_hang")
    private LocalDateTime ngaynhap;

    @Column(name = "hinh_anh")
    private String hinhAnh;

        public Product(){}

    public Product(int id, String ten, String phanloai, String nhaCungCap, int dungtich, String moTa, double gia, int giamGia, int soLuongTonKho, int daban, LocalDateTime ngaynhap, String hinhAnh) {
        this.id = id;
        this.ten = ten;
        this.phanloai = phanloai;
        this.nhaCungCap = nhaCungCap;
        this.dungtich = dungtich;
        this.moTa = moTa;
        this.gia = gia;
        this.giamGia = giamGia;
        this.soLuongTonKho = soLuongTonKho;
        this.daban = daban;
        this.ngaynhap = ngaynhap;
        this.hinhAnh = hinhAnh;
    }

        public String getTen() {
            return ten;
        }

        public String getMoTa() {
            return moTa;
        }

        public void setMoTa(String moTa) {
            this.moTa = moTa;
        }

        public String getNhaCungCap() {
            return nhaCungCap;
        }

        public void setNhaCungCap(String nhaCungCap) {
            this.nhaCungCap = nhaCungCap;
        }

        public void setTen(String ten) {
            this.ten = ten;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhanloai() {
            return phanloai;
        }

        public void setPhanloai(String phanloai) {
            this.phanloai = phanloai;
        }

        public int getDungtich() {
            return dungtich;
        }

        public void setDungtich(int dungtich) {
            this.dungtich = dungtich;
        }

        public double getGia() {
            return gia;
        }

        public void setGia(double gia) {
            this.gia = gia;
        }

        public int getGiamGia() {
            return giamGia;
        }

        public void setGiamGia(int giamGia) {
            this.giamGia = giamGia;
        }

        public int getSoLuongTonKho() {
            return soLuongTonKho;
        }

        public void setSoLuongTonKho(int soLuongTonKho) {
            this.soLuongTonKho = soLuongTonKho;
        }

        public int getDaban() {
            return daban;
        }

        public void setDaban(int daban) {
            this.daban = daban;
        }

        public LocalDateTime getNgaynhap() {
            return ngaynhap;
        }

        public void setNgaynhap(LocalDateTime ngaynhap) {
            this.ngaynhap = ngaynhap;
        }

        public String getHinhAnh() {
            return hinhAnh;
        }

        public void setHinhAnh(String hinhAnh) {
            this.hinhAnh = hinhAnh;
        }
}
