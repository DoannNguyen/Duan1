package com.example.assigment_duan1.Model;

import java.sql.Date;

public class HoaDon {
    private int maHoaDon, maSanPham;
    private String nguoiTao, tenNguoiTao, tenSanPham;
    private Date ngay;
    private double donGia;

    public HoaDon(int maHoaDon, int maSanPham, String nguoiTao, String tenNguoiTao, String tenSanPham, Date ngay, double donGia) {
        this.maHoaDon = maHoaDon;
        this.maSanPham = maSanPham;
        this.nguoiTao = nguoiTao;
        this.tenNguoiTao = tenNguoiTao;
        this.tenSanPham = tenSanPham;
        this.ngay = ngay;
        this.donGia = donGia;
    }

    public HoaDon() {
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public String getTenNguoiTao() {
        return tenNguoiTao;
    }

    public void setTenNguoiTao(String tenNguoiTao) {
        this.tenNguoiTao = tenNguoiTao;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
}
