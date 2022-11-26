package com.example.assigment_duan1.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;

import com.example.assigment_duan1.Database.DB_Helper;
import com.example.assigment_duan1.Model.HoaDon;
import com.example.assigment_duan1.Model.ThongKe;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {
    private SQLiteDatabase db;

    public HoaDonDAO(Context context) {
        DB_Helper helper = new DB_Helper(context);
        db = helper.getWritableDatabase();
    }

    //tạo phương thức insert
    public long insert(HoaDon hoaDon){
        ContentValues values = new ContentValues();
        values.put("nguoiTao",hoaDon.getNguoiTao());
        values.put("maSanPham",hoaDon.getMaSanPham());
        values.put("tenSanPham",hoaDon.getTenSanPham());
        values.put("donGia",hoaDon.getDonGia());
        values.put("ngayTao", String.valueOf(hoaDon.getNgay()));
        values.put("tenNguoiTao",hoaDon.getTenNguoiTao());
        return db.insert("hoaDon",null,values);
    }

    //tạo phương thức update
    public int update(HoaDon hoaDon){
        ContentValues values = new ContentValues();
        values.put("nguoiTao",hoaDon.getNguoiTao());
        values.put("tenSanPham",hoaDon.getTenSanPham());
        values.put("maSanPham",hoaDon.getMaSanPham());
        values.put("donGia",hoaDon.getDonGia());
        values.put("ngay", String.valueOf(hoaDon.getNgay()));
        values.put("tenNguoiTao",hoaDon.getTenNguoiTao());
        return db.update("hoaDon",values,"maHoaDon = ?", new String[]{String.valueOf(hoaDon.getMaHoaDon())});
    }

    //tạo phuong thức xóa
    public int delete(String id){
        return db.delete("hoaDon","maHoaDon = ?", new String[]{id} );
    }

    //phuong thức lấy toàn bộ dữ liệu hóa đơn
    public List<HoaDon> getAll(){
        String sql = "SELECT * FROM hoaDon";
        return getData(sql);
    }

    //phương thức lấy hóa đơn theo id
    public HoaDon getid(String id){
        String sql = "SELECT * FROM hoaDon WHERE maHoaDon = ?";
        List<HoaDon> list = getData(sql, id);
        return list.get(0);
    }

    //phuong thức lấy dữ liệu
    @SuppressLint("Range")
    private List<HoaDon> getData(String sql , String ... selectionArgs){
        List<HoaDon> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            HoaDon hoaDon = new HoaDon();
            hoaDon.setMaHoaDon(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maHoaDon"))));
            hoaDon.setNguoiTao(cursor.getString(cursor.getColumnIndex("nguoiTao")));
            hoaDon.setTenSanPham(cursor.getString(cursor.getColumnIndex("tenSanPham")));
            hoaDon.setMaSanPham(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSanPham"))));
            hoaDon.setDonGia(Double.parseDouble(cursor.getString(cursor.getColumnIndex("donGia"))));
            hoaDon.setNgay(Date.valueOf(cursor.getString(cursor.getColumnIndex("ngayTao"))));
            hoaDon.setTenNguoiTao(cursor.getString(cursor.getColumnIndex("tenNguoiTao")));
            list.add(hoaDon);
        }
       return list;
    }

    @SuppressLint("Range")
    private List<ThongKe> getData1(String sql, String ...selectionArgs){
        List<ThongKe> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            ThongKe thongKe = new ThongKe();
            thongKe.setTensp(cursor.getString(cursor.getColumnIndex("tenSP")));
            thongKe.setTienThongKe(Double.parseDouble(cursor.getString(cursor.getColumnIndex("tienThongKe"))));
            list.add(thongKe);
        }
        return list;
    }

    //tạo phương thức thống kê dữ liệu hóa đơn
    public List<ThongKe> getDoanhthu(){
        String sql = "select a.tenSanPham as tenSP, a.maSanPham, sum(b.donGia) as tienThongKe from sanPham a inner join hoaDon b" +
                " on a.maSanPham = b.maSanPham group by a.maSanPham, a.tenSanPham";
        return getData1(sql);
    }
}
