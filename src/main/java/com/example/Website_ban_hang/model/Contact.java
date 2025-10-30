package com.example.Website_ban_hang.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "lienhe")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lien_he")
    private int idLienHe;

    @ManyToOne
    @JoinColumn(name = "id_nguoi_dung", nullable = true)
    private User user;

    @Column(name = "ho_ten", nullable = false)
    private String hoTen;

    @Column(name = "sdt", nullable = false)
    private String sdt;

    @Column(name = "noi_dung", columnDefinition = "TEXT", nullable = false)
    private String noiDung;

    @Column(name = "ngay_gui")
    private LocalDateTime ngayGui = LocalDateTime.now();

    public Contact() {}

    public int getIdLienHe() {
        return idLienHe;
    }

    public void setIdLienHe(int idLienHe) {
        this.idLienHe = idLienHe;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public LocalDateTime getNgayGui() {
        return ngayGui;
    }

    public void setNgayGui(LocalDateTime ngayGui) {
        this.ngayGui = ngayGui;
    }
}
