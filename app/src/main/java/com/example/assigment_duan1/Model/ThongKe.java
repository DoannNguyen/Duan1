package com.example.assigment_duan1.Model;

import com.github.mikephil.charting.components.Description;

public class ThongKe {

    private double tienThongKe;
    private String tensp;

    public ThongKe() {
    }

    public ThongKe( double tienThongKe, String tensp) {

        this.tienThongKe = tienThongKe;
        this.tensp = tensp;
    }

    public double getTienThongKe() {
        return tienThongKe;
    }

    public void setTienThongKe(double tienThongKe) {
        this.tienThongKe = tienThongKe;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }
}
