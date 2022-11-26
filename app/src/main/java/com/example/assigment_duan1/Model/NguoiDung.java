package com.example.assigment_duan1.Model;

import java.util.Date;

public class NguoiDung {
    private String username, password, tenNguoiDung, email, sdt, namSinh;
    private int type;

    public NguoiDung() {
    }

    public NguoiDung(String username, String password, String tenNguoiDung, String email, String sdt, String namSinh, int type) {
        this.username = username;
        this.password = password;
        this.tenNguoiDung = tenNguoiDung;
        this.email = email;
        this.sdt = sdt;
        this.namSinh = namSinh;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
