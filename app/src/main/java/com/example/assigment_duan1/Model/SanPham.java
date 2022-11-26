package com.example.assigment_duan1.Model;

public class SanPham {
    private int maSanPham, soLuong;
    private String tenSanPham, loaiSanPham;
    private double donGia;

    public SanPham() {
    }

    public SanPham(int maSanPham, int soLuong, String tenSanPham, String loaiSanPham, double donGia) {
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.tenSanPham = tenSanPham;
        this.loaiSanPham = loaiSanPham;
        this.donGia = donGia;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getLoaiSanPham() {
        return loaiSanPham;
    }

    public void setLoaiSanPham(String loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
}
